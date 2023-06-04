package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.GetEPAction;
import shadowverse.characters.Vampire;

public class MonoResolve extends CustomCard {
    public static final String ID = "shadowverse:MonoResolve";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MonoResolve");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MonoResolve.png";

    public MonoResolve() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction) new SFXAction("MonoResolve"));
        addToBot((AbstractGameAction)new GetEPAction(true,1));
    }

    public AbstractCard makeCopy() {
        return new MonoResolve();
    }
}
