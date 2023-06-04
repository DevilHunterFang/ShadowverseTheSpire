package shadowverse.cards.Vampire.Wrath;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.LustfulDesire;
import shadowverse.cards.Neutral.Temp.WingsOfDesire;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.OmenOfLustPower;
import shadowverse.powers.WrathPower;

import java.util.ArrayList;
import java.util.List;


public class OmenOfLust
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfLust";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfLust");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfLust2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfLust.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfLust2.png";

    public OmenOfLust() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("OmenOfLust"));
                if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }else {
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new OmenOfLustPower(abstractPlayer,this.magicNumber),this.magicNumber));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("OmenOfLust2"));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(new WingsOfDesire()));
                if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
                    addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,this.magicNumber),this.magicNumber));
                    addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DexterityPower(abstractPlayer,this.magicNumber),this.magicNumber));
                    addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(new LustfulDesire(),7,true,true));
                }
                break;
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfLust();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfLust.this.timesUpgraded;
                OmenOfLust.this.upgraded = true;
                OmenOfLust.this.name = NAME + "+";
                OmenOfLust.this.initializeTitle();
                OmenOfLust.this.baseMagicNumber = 2;
                OmenOfLust.this.magicNumber = OmenOfLust.this.baseMagicNumber;
                OmenOfLust.this.upgradedMagicNumber = true;
                OmenOfLust.this.baseDamage = 11;
                OmenOfLust.this.upgradedDamage = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfLust.this.timesUpgraded;
                OmenOfLust.this.upgraded = true;
                OmenOfLust.this.textureImg = IMG_PATH2;
                OmenOfLust.this.loadCardImage(IMG_PATH2);
                OmenOfLust.this.name = cardStrings2.NAME;
                OmenOfLust.this.initializeTitle();
                OmenOfLust.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfLust.this.initializeDescription();
                OmenOfLust.this.baseMagicNumber = 2;
                OmenOfLust.this.magicNumber = OmenOfLust.this.baseMagicNumber;
                OmenOfLust.this.upgradedMagicNumber = true;
                OmenOfLust.this.baseDamage = 14;
                OmenOfLust.this.upgradedDamage = true;
                OmenOfLust.this.cardsToPreview = new WingsOfDesire();
                OmenOfLust.this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
            }
        });
        return list;
    }
}


