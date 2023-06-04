package shadowverse.cards.Bishop.Amulet1;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.cards.Neutral.Temp.Muse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.MusePrincessPower;


public class MusePrincess extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:MusePrincess";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MusePrincess");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MusePrincess.png";

    public MusePrincess() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF, 3);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.baseBlock = 9;
        this.cardsToPreview = new Muse();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    public AbstractCard makeCopy() {
        return new MusePrincess();
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        if (!p.hasPower(MusePrincessPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new MusePrincessPower(p)));
        addToBot(new MakeTempCardInHandAction(new NaterranGreatTree(), 3));
        addToBot(new GainEnergyAction(1));
        addToBot(new SFXAction("MusePrincess_EH"));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        if (!p.hasPower(MusePrincessPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new MusePrincessPower(p)));
        addToBot(new SFXAction("MusePrincess"));
        addToBot(new MakeTempCardInHandAction(new NaterranGreatTree()));
    }
}

