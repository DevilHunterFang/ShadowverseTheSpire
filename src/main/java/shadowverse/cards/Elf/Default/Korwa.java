package shadowverse.cards.Elf.Default;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.Fil;
import shadowverse.characters.Elf;
import shadowverse.powers.FilPower;


public class Korwa extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Korwa";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Korwa");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Korwa.png";

    public Korwa() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF,3);
        this.cardsToPreview = new Fil();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Korwa"));
        if (!p.hasPower("shadowverse:FilPower"))
            addToBot(new ApplyPowerAction(p, p, new FilPower(p)));
        addToBot(new MakeTempCardInHandAction(new Fil(), 3));
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Korwa"));
        if (!p.hasPower("shadowverse:FilPower"))
            addToBot(new ApplyPowerAction(p, p, new FilPower(p)));
    }


    public AbstractCard makeCopy() {
        return new Korwa();
    }
}

