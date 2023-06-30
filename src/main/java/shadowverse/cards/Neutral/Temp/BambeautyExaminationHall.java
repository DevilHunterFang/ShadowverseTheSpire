package shadowverse.cards.Neutral.Temp;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class BambeautyExaminationHall extends AbstractAmuletCard {
    public static final String ID = "shadowverse:BambeautyExaminationHall";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BambeautyExaminationHall");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BambeautyExaminationHall.png";

    public BambeautyExaminationHall() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.countDown = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
        addToBot(new DrawCardAction(this.magicNumber));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
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
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new SFXAction("BambeautyExaminationHall"));
        addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
        addToBot(new DrawCardAction(this.magicNumber));
        addToBot(new PlaceAmulet(this.makeStatEquivalentCopy(),null));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("BambeautyExaminationHall"));
        addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
        addToBot(new DrawCardAction(this.magicNumber));
    }
}
