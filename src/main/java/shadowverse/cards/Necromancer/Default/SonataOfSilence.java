package shadowverse.cards.Necromancer.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;
import shadowverse.powers.ICPower;
import shadowverse.powers.JudgmentWordPower;


public class SonataOfSilence
        extends CustomCard {
    public static final String ID = "shadowverse:SonataOfSilence";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SonataOfSilence");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SonataOfSilence.png";

    public SonataOfSilence() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("SonataOfSilence"));
            for (AbstractPower pow : abstractPlayer.powers) {
                if (!(pow instanceof StrengthPower)&&!(pow instanceof DexterityPower)&&!(pow instanceof Cemetery)&&!(pow instanceof ICPower)&&!(pow instanceof JudgmentWordPower)) {
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner, abstractPlayer, pow.ID));
                }
            }
            addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SonataOfSilence();
    }
}

