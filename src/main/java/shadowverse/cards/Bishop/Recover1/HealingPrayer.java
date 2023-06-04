package shadowverse.cards.Bishop.Recover1;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReduceAllCountDownAction;
import shadowverse.characters.Bishop;

public class HealingPrayer extends CustomCard {
    public static final String ID = "shadowverse:HealingPrayer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HealingPrayer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HealingPrayer.png";

    public HealingPrayer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new HealAction(p,p,3));
        addToBot((AbstractGameAction)new ReduceAllCountDownAction(this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return new HealingPrayer();
    }
}
