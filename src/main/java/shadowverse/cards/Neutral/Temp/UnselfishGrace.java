package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import shadowverse.characters.Vampire;
import shadowverse.powers.TheTemperancePower;

public class UnselfishGrace extends CustomCard {
    public static final String ID = "shadowverse:UnselfishGrace";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnselfishGrace");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UnselfishGrace.png";

    public UnselfishGrace() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeMagicNumber(1);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("UnselfishGrace"));
        addToBot((AbstractGameAction) new ApplyPowerAction(p, p, new TheTemperancePower(p, this.magicNumber,3), this.magicNumber));
        addToBot((AbstractGameAction) new ApplyPowerAction(p,p, new RegenPower(p,2)));
    }

    public AbstractCard makeCopy() {
        return new UnselfishGrace();
    }
}
