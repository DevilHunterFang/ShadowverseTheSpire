package shadowverse.cards.Bishop.MechLion;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.Neutral.Temp.LionCrystalCopy;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class LionTemple extends CustomCard implements AbstractNoCountDownAmulet{
    public static final String ID = "shadowverse:LionTemple";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LionTemple");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LionTemple.png";
    private int count = 5;


    public LionTemple() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION,CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new LionCrystal();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded)
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new LionCrystalCopy()));
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }


    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
        this.count = 5;
    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {
        if (this.count>0){
            if (c.cardID.contains("shadowverse:LionCrystal")){
                count--;
                addToBot((AbstractGameAction)new GainEnergyAction(1));
            }
        }
    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

}
