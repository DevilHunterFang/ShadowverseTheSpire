package shadowverse.cards.Bishop.Evil;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Neutral.Curse.Indulgence;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class DirtyPriest
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:DirtyPriest";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DirtyPriest");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DirtyPriest.png";

    public DirtyPriest() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.cardsToPreview = new Indulgence();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.POWER;
        }else {
            if (this.type==CardType.POWER){
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.type==CardType.POWER && this.costForTurn == 0){
            addToBot(new SFXAction("DirtyPriest_Acc"));
        }else {
            addToBot(new SFXAction("DirtyPriest"));
            addToBot(new GainBlockAction(p,this.block));
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
            addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview,1,true,true,false));
            addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview,1));
            if (this.type == CardType.POWER){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DirtyPriest();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {
    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

    @Override
    public int returnCountDown() {
        return 2;
    }
}


