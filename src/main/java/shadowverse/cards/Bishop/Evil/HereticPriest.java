package shadowverse.cards.Bishop.Evil;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.characters.Bishop;
import shadowverse.powers.HereticPriestPower;

public class HereticPriest
        extends CustomCard {
    public static final String ID = "shadowverse:HereticPriest";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HereticPriest");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HereticPriest.png";

    public HereticPriest() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new EvilWorship();
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("HereticPriest"));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.cardsToPreview.makeStatEquivalentCopy(),3));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new HereticPriestPower((AbstractCreature) p,this.magicNumber)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new HereticPriest();
    }
}


