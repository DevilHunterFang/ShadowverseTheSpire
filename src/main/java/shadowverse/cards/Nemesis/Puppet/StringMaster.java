package shadowverse.cards.Nemesis.Puppet;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.Puppet;
import shadowverse.characters.Nemesis;
import shadowverse.powers.StringMasterPower;


public class StringMaster extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:StringMaster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StringMaster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/StringMaster.png";

    public StringMaster() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF,2);
        this.cardsToPreview = new Puppet();
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("StringMaster"));
        addToBot(new ApplyPowerAction(p, p, new StringMasterPower(p, 1), 1));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("StringMaster"));
        addToBot( new ApplyPowerAction(p, p, new StringMasterPower(p, 1), 1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new StringMaster();
    }
}

