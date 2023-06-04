package shadowverse.cards.Vampire.Bat;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NightmareAction;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.Vampire;


public class UnleashTheNightmare
        extends CustomCard {
    public static final String ID = "shadowverse:UnleashTheNightmare";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnleashTheNightmare");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UnleashTheNightmare.png";

    public UnleashTheNightmare() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard)new ProductMachine();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new NightmareAction(this.magicNumber,CardType.ATTACK));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new UnleashTheNightmare();
    }
}

