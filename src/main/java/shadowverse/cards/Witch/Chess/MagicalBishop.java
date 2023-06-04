package shadowverse.cards.Witch.Chess;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.MagicalPawn;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.MagicalBishopPower;

public class MagicalBishop
        extends CustomCard {
    public static final String ID = "shadowverse:MagicalBishop";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagicalBishop");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MagicalBishop.png";

    public MagicalBishop() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
        this.cardsToPreview = new MagicalPawn();
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
        addToBot( new ApplyPowerAction(p, p, new MagicalBishopPower(p,3)));
        addToBot( new MakeTempCardInHandAction(this.cardsToPreview,this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MagicalBishop();
    }
}


