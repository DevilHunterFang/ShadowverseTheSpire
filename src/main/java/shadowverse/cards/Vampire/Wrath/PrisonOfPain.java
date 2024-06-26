package shadowverse.cards.Vampire.Wrath;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.PrisonOfPainPower;

public class PrisonOfPain
        extends CustomCard {
    public static final String ID = "shadowverse:PrisonOfPain";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PrisonOfPain");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PrisonOfPain.png";

    public PrisonOfPain() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new DrawCardAction(1));
        addToBot(new ApplyPowerAction(p, p, new PrisonOfPainPower(p, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new PrisonOfPain();
    }
}


