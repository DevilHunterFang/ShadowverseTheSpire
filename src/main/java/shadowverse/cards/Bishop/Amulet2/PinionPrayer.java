package shadowverse.cards.Bishop.Amulet2;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.Neutral.Temp.HolyFalcon;
import shadowverse.cards.Neutral.Temp.RegalFalcon;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class PinionPrayer extends AbstractAmuletCard {
    public static final String ID = "shadowverse:PinionPrayer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PinionPrayer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PinionPrayer.png";


    public PinionPrayer() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
        this.countDown = 2;
        this.cardsToPreview = new HolyFalcon();
    }



    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return 0;
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
