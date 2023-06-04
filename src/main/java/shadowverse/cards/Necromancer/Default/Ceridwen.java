package shadowverse.cards.Necromancer.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.BurialAction;
import shadowverse.action.CeridwenAction;
import shadowverse.action.ChoiceAction2;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.Neutral.Temp.EternalPotion;
import shadowverse.cards.Neutral.Temp.InstantPotion;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;

import java.util.ArrayList;
import java.util.List;


public class Ceridwen extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Ceridwen";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceridwen");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceridwen2");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceridwen3");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ceridwen.png";
    public static final String IMG_PATH2 = "img/cards/Ceridwen2.png";
    public static final String IMG_PATH3 = "img/cards/Ceridwen3.png";
    private boolean branch2 = false;
    private boolean branch3 = false;
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new EternalPotion());
        list.add(new InstantPotion());
        return list;
    }

    public Ceridwen() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }

    public void update() {
        if (branch3){
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                    Shadowverse.Accelerate(this)){
                setCostForTurn(0);
                this.type = CardType.POWER;
            }else {
                if (this.type==CardType.POWER){
                    setCostForTurn(3);
                    this.type = CardType.ATTACK;
                }
            }
        }
        super.update();
        if (branch2) {
            if (this.hb.hovered)
                if (this.rotationTimer <= 0.0F) {
                    this.rotationTimer = 2.0F;
                    this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                    if (this.previewIndex == returnProphecy().size() - 1) {
                        this.previewIndex = 0;
                    } else {
                        this.previewIndex++;
                    }
                } else {
                    this.rotationTimer -= Gdx.graphics.getDeltaTime();
                }
        }
    }

    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot(new SFXAction("Ceridwen"));
                addToBot(new GainBlockAction(abstractPlayer, this.block));
                if (this.costForTurn > 0) {
                    addToBot(new ReanimateAction(3));
                }
                break;
            case 1:
                addToBot(new SFXAction("Ceridwen2"));
                addToBot(new GainBlockAction(abstractPlayer, this.block));
                AbstractCard eternal = new EternalPotion();
                AbstractCard instant = new InstantPotion();
                addToBot(new GainEnergyAction(1));
                addToBot(new ChoiceAction2(eternal, instant));
                break;
            case 2:
                if (this.type==CardType.POWER && this.costForTurn == 0){
                    addToBot(new SFXAction("Ceridwen3_Acc"));
                    addToBot(new BurialAction(1,new DrawCardAction(1)));
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    if (abstractPlayer.hasPower(MementoPower.POWER_ID)){
                        addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    }
                }else {
                    addToBot(new SFXAction("Ceridwen3"));
                    addToBot(new GainBlockAction(abstractPlayer, this.block));
                    if (this.costForTurn > 0) {
                        addToBot(new ReanimateAction(4));
                        addToBot(new CeridwenAction(4));
                    }
                    if (EnergyPanel.getCurrentEnergy() < 6){
                        addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
                    }
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return new Ceridwen();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ceridwen.this.timesUpgraded;
                Ceridwen.this.upgraded = true;
                Ceridwen.this.name = NAME + "+";
                Ceridwen.this.initializeTitle();
                Ceridwen.this.baseBlock = 12;
                Ceridwen.this.upgradedBlock = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ceridwen.this.timesUpgraded;
                Ceridwen.this.upgraded = true;
                Ceridwen.this.textureImg = IMG_PATH2;
                Ceridwen.this.loadCardImage(IMG_PATH2);
                Ceridwen.this.name = cardStrings2.NAME;
                Ceridwen.this.initializeTitle();
                Ceridwen.this.rawDescription = cardStrings2.DESCRIPTION;
                Ceridwen.this.initializeDescription();
                Ceridwen.this.branch2 = true;
                Ceridwen.this.rarity = CardRarity.RARE;
                Ceridwen.this.setDisplayRarity(Ceridwen.this.rarity);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ceridwen.this.timesUpgraded;
                Ceridwen.this.upgraded = true;
                Ceridwen.this.textureImg = IMG_PATH3;
                Ceridwen.this.loadCardImage(IMG_PATH3);
                Ceridwen.this.name = cardStrings3.NAME;
                Ceridwen.this.initializeTitle();
                Ceridwen.this.rawDescription = cardStrings3.DESCRIPTION;
                Ceridwen.this.initializeDescription();
                Ceridwen.this.branch3 = true;
                Ceridwen.this.rarity = CardRarity.RARE;
                Ceridwen.this.setDisplayRarity(Ceridwen.this.rarity);
                Ceridwen.this.upgradeBaseCost(3);
                Ceridwen.this.baseBlock = 12;
                Ceridwen.this.upgradedBlock = true;
            }
        });
        return list;
    }
}

