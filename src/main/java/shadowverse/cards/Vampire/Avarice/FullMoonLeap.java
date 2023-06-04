package shadowverse.cards.Vampire.Avarice;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Vampire;


public class FullMoonLeap
        extends CustomCard {
    public static final String ID = "shadowverse:FullMoonLeap";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FullMoonLeap");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FullMoonLeap.png";

    public FullMoonLeap() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));
        if (abstractPlayer.hand.group.size() < 4) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PenNibPower(abstractPlayer, 1), 1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new FullMoonLeap();
    }
}

