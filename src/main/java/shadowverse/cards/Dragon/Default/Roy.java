package shadowverse.cards.Dragon.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.ChoiceAction2;
import shadowverse.action.DestroyAction;
import shadowverse.cards.Dragon.Tempo.OmenOfDisdain;
import shadowverse.cards.Neutral.Temp.DragonlifeBlade;
import shadowverse.cards.Neutral.Temp.DragonstrifeBlade;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;

import java.util.ArrayList;
import java.util.List;

public class Roy extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Roy";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Roy");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Roy2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Roy.png";
    public static final String IMG_PATH2 = "img/cards/Roy2.png";
    private boolean branch2 = false;

    private float rotationTimer;
    private int previewIndex;

    public int enhanceCost;
    public int baseCost;

    public boolean exFreeOnce;

    public int exCost;

    public int exCostForTurn;

    public int ex;


    public Roy() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseCost = cost;
        this.enhanceCost = 4;
        this.exCost = cost;
        this.exCostForTurn = cost;
        this.exFreeOnce = false;
        this.ex = 0;
    }

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new DragonlifeBlade());
        list.add(new DragonstrifeBlade());
        return list;
    }

    public void update() {
        super.update();
        if (!branch2) {
            if (this.hb.hovered)
                if (this.rotationTimer <= 0.0F) {
                    this.rotationTimer = 2.0F;
                    this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
                    if (this.previewIndex == returnChoice().size() - 1) {
                        this.previewIndex = 0;
                    } else {
                        this.previewIndex++;
                    }
                    if (this.upgraded)
                        this.cardsToPreview.upgrade();
                } else {
                    this.rotationTimer -= Gdx.graphics.getDeltaTime();
                }
        } else {
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                if (Shadowverse.Enhance(enhanceCost)) {
                    if (this.ex == 0) {
                        this.exCost = this.cost;
                        this.exCostForTurn = this.costForTurn;
                    }
                    this.ex = 1;
                    this.exFreeOnce = this.freeToPlayOnce;
                    setCostForTurn(enhanceCost);
                } else {
                    if (this.ex > 0) {
                        setCostForTurn(2);
                        this.cost = this.exCost;
                        this.costForTurn = this.exCostForTurn;
                        this.freeToPlayOnce = this.exFreeOnce;
                    }
                    this.ex = 0;
                }
            }
        }
    }

    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot(new SFXAction("Roy"));
                addToBot(new GainBlockAction(abstractPlayer, this.block));
                AbstractCard l = new DragonlifeBlade();
                AbstractCard s = new DragonstrifeBlade();
                if (this.upgraded){
                    l.upgrade();
                    s.upgrade();
                }
                addToBot(new ChoiceAction2(l,s));
                break;
            case 1:
                if (this.costForTurn == 4) {
                    addToBot(new SFXAction("Roy2_Eh"));
                    addToBot(new DestroyAction(1, (new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage * 2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY))));
                    addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(9, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
                } else {
                    addToBot(new SFXAction("Roy2"));
                    addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
                addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Roy();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Roy.this.timesUpgraded;
                Roy.this.upgraded = true;
                Roy.this.name = NAME + "+";
                Roy.this.initializeTitle();
                Roy.this.baseBlock = 9;
                Roy.this.upgradedBlock = true;
                Roy.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Roy.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Roy.this.timesUpgraded;
                Roy.this.baseDamage = 20;
                Roy.this.upgraded = true;
                Roy.this.textureImg = IMG_PATH2;
                Roy.this.loadCardImage(IMG_PATH2);
                Roy.this.name = cardStrings2.NAME;
                Roy.this.initializeTitle();
                Roy.this.rawDescription = cardStrings2.DESCRIPTION;
                Roy.this.initializeDescription();
                Roy.this.upgradeBaseCost(2);
                Roy.this.branch2 = true;
                Roy.this.target = CardTarget.ENEMY;
                Roy.this.upgraded = true;
            }
        });
        return list;
    }
}

