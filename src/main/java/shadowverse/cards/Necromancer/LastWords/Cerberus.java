package shadowverse.cards.Necromancer.LastWords;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.BurialAction;
import shadowverse.action.CerberusAction;
import shadowverse.cards.Neutral.Temp.Koko;
import shadowverse.cards.Neutral.Temp.Mimi;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.List;


public class Cerberus
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Cerberus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cerberus");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cerberus2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cerberus.png";
    public static final String IMG_PATH2 = "img/cards/Cerberus2.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Mimi());
        list.add(new Koko());
        return list;
    }

    public Cerberus() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 9;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
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
    }

    public void applyPowers() {
        super.applyPowers();
        if (chosenBranch() == 1){
            int count = 0;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
                    count++;
            }
            this.rawDescription = cardStrings2.DESCRIPTION;
            this.rawDescription += cardStrings2.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings2.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction("Cerberus"));
                addToBot(new VFXAction(abstractPlayer, new InflameEffect(abstractPlayer), 1.0F));
                ArrayList<AbstractCard> dog = returnChoice();
                for (AbstractCard c : dog) {
                    if (this.upgraded) {
                        c.upgrade();
                    }
                    addToBot(new MakeTempCardInHandAction(c, 1));
                }
                break;
            case 1:
                addToBot(new SFXAction("Cerberus2"));
                addToBot(new VFXAction(abstractPlayer, new InflameEffect(abstractPlayer), 1.0F));
                addToBot(new BurialAction(1,new CerberusAction()));
                int count = 0;
                for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                    if (c.hasTag(AbstractShadowversePlayer.Enums.LASTWORD))
                        count++;
                }
                if (count>=10){
                    if (!abstractPlayer.hasPower(DoubleDamagePower.POWER_ID)){
                        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DoubleDamagePower(abstractPlayer,1,false)));
                    }
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cerberus();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Cerberus.this.timesUpgraded;
                Cerberus.this.upgraded = true;
                Cerberus.this.name = NAME + "+";
                Cerberus.this.initializeTitle();
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Cerberus.this.timesUpgraded;
                Cerberus.this.upgraded = true;
                Cerberus.this.textureImg = IMG_PATH2;
                Cerberus.this.loadCardImage(IMG_PATH2);
                Cerberus.this.name = cardStrings2.NAME;
                Cerberus.this.initializeTitle();
                Cerberus.this.upgradeBaseCost(1);
                Cerberus.this.rawDescription = cardStrings2.DESCRIPTION;
                Cerberus.this.initializeDescription();
            }
        });
        return list;
    }
}

