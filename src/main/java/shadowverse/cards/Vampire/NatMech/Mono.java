package shadowverse.cards.Vampire.NatMech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.FirstOne;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.MonoPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;
import java.util.List;


public class Mono
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Mono";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Mono");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Mono2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Mono.png";
    public static final String IMG_PATH2 = "img/cards/Mono2.png";
    private boolean previewBranch = true;
    private static AbstractCard firstOne = (AbstractCard)new FirstOne();
    private static AbstractCard upgradedFirstOne(){
        AbstractCard c = (AbstractCard)new FirstOne();
        c.upgrade();
        return c;
    }

    public Mono() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseBlock = 6;
        this.cardsToPreview = (AbstractCard) new FirstOne();
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void update() {
        super.update();
        if (this.previewBranch) {
            if (this.upgraded) {
                this.cardsToPreview = upgradedFirstOne();
            } else
                this.cardsToPreview = firstOne;
        }
    }


    public void applyPowers() {
        if (chosenBranch()==0){
            super.applyPowers();
            int count = 0;
            for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
                if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type!=CardType.SKILL){
                    count++;
                }
            }
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void atTurnStart(){
        if (chosenBranch()==1)
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new MonoPower(AbstractDungeon.player,0)));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Mono"));
                addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                int mCount = 0;
                for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
                    if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type!=CardType.SKILL){
                        mCount++;
                    }
                }
                if (mCount>=7){
                    AbstractCard ca = null;
                    if (this.upgraded){
                        ca =upgradedFirstOne().makeStatEquivalentCopy();
                    }else {
                        ca =firstOne.makeStatEquivalentCopy();
                    }
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(ca));
                }
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Mono2"));
                addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)){
                    addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1), 1));
                }
                int pCount = 0;
                AbstractPower monoPower = null;
                for (AbstractPower power:abstractPlayer.powers){
                    if (power instanceof MonoPower)
                        monoPower = power;
                }
                if (monoPower!=null&&monoPower.amount>=3||(monoPower!=null&&monoPower.amount>=2&&(abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)))){
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    addToBot((AbstractGameAction)new ArmamentsAction(true));
                    addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer, abstractPlayer, new DoubleDamagePower(abstractPlayer, 1, false), 1));
                }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Mono();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Mono.this.timesUpgraded;
                Mono.this.upgraded = true;
                Mono.this.name = NAME + "+";
                Mono.this.initializeTitle();
                Mono.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Mono.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Mono.this.timesUpgraded;
                Mono.this.upgraded = true;
                Mono.this.textureImg = IMG_PATH2;
                Mono.this.loadCardImage(IMG_PATH2);
                Mono.this.name = cardStrings2.NAME;
                Mono.this.initializeTitle();
                Mono.this.rawDescription = cardStrings2.DESCRIPTION;
                Mono.this.initializeDescription();
                Mono.this.previewBranch = false;
                Mono.this.baseDamage = 36;
                Mono.this.upgradedDamage = true;
            }
        });
        return list;
    }
}


