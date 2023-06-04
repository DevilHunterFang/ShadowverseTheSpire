package shadowverse.cards.Royal.Default;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.TheChariot_I;
import shadowverse.characters.Royal;
import shadowverse.powers.TheChariotPower;
import shadowverse.powers.TheChariot_IPower;

public class TheChariot extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:TheChariot";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheChariot");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheChariot.png";

    public TheChariot() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF, 3);
        this.baseBlock = 10;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new TheChariot_I();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(2);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("TheChariot_I"));
        if (this.upgraded) {
            addToBot(new ApplyPowerAction(p, p, new TheChariot_IPower(p, 70), 70));
        } else {
            addToBot(new ApplyPowerAction(p, p, new TheChariot_IPower(p, 63), 63));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("TheChariot"));
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new TheChariotPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheChariot();
    }
}


