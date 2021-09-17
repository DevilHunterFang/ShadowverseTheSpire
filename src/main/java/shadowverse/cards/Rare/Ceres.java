package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Temp.EternalVow;
import shadowverse.cards.Temp.ImmoralDesire;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.List;


public class Ceres
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Ceres";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceres");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceres2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ceres.png";
    public static final String IMG_PATH2 = "img/cards/Ceres2.png";
    private int branchPreview = 0;
    private static AbstractCard vow = (AbstractCard)new EternalVow();
    private static AbstractCard immoral = (AbstractCard)new ImmoralDesire();
    private static  AbstractCard upgradedVow(){
      AbstractCard c = (AbstractCard)new EternalVow();
      c.upgrade();
      return c;
    }

    public Ceres() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 12;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void update(){
        super.update();
        if (this.branchPreview==1){
            this.cardsToPreview = immoral;
        }else {
            if (this.upgraded){
                this.cardsToPreview =  upgradedVow();
            }else
                this.cardsToPreview = vow;
        }
    }

    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Ceres"));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new RegenPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ThornsPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
                if (this.upgraded){
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(upgradedVow().makeStatEquivalentCopy()));
                }else {
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(vow.makeStatEquivalentCopy()));
                }
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Ceres2"));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(immoral.makeStatEquivalentCopy()));
                break;
            default:
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Ceres();
    }

    @Override
    public void triggerOnExhaust() {
        if (chosenBranch()==1){
            addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ceres.this.timesUpgraded;
                Ceres.this.upgraded = true;
                Ceres.this.name = NAME + "+";
                Ceres.this.initializeTitle();
                Ceres.this.baseBlock = 15;
                Ceres.this.upgradedBlock = true;
                Ceres.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ceres.this.timesUpgraded;
                Ceres.this.upgraded = true;
                Ceres.this.textureImg = IMG_PATH2;
                Ceres.this.loadCardImage(IMG_PATH2);
                Ceres.this.name = cardStrings2.NAME;
                Ceres.this.initializeTitle();
                Ceres.this.rawDescription = cardStrings2.DESCRIPTION;
                Ceres.this.initializeDescription();
                Ceres.this.branchPreview = 1;
                Ceres.this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
                upgradeBaseCost(1);
                Ceres.this.baseBlock = 12;
                Ceres.this.upgradedBlock = true;
            }
        });

        return list;
    }
}

