package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Bishop;

public class ErisPrayer extends CustomCard {
    public static final String ID = "shadowverse:ErisPrayer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ErisPrayer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ErisPrayer.png";

    public ErisPrayer() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction) new SFXAction("ErisPrayer"));
        addToBot((AbstractGameAction) new DrawCardAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ErisPrayer();
    }
}
