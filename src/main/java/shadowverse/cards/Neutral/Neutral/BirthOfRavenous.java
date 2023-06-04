package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.powers.BirthOfRavenousPower;


public class BirthOfRavenous
        extends AbstractNeutralCard {
    public static final String ID = "shadowverse:BirthOfRavenous";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BirthOfRavenous");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BirthOfRavenous.png";

    public BirthOfRavenous() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("BirthOfRavenous"));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer,abstractPlayer,new BirthOfRavenousPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BirthOfRavenous();
    }
}

