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
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Neutral.Curse.Indulgence;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class ImpiousBishop
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:ImpiousBishop";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImpiousBishop");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ImpiousBishop.png";

    public ImpiousBishop() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
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
            addToBot(new SFXAction("ImpiousBishop_Acc"));
        }else {
            addToBot(new SFXAction("ImpiousBishop"));
            addToBot(new GainBlockAction(p,this.block));
            addToBot(new ApplyPowerAction(p,p,new NextTurnBlockPower(p,this.block),this.block));
            addToBot(new HealAction(p,p,3));
            addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview,2));
            if (EnergyPanel.getCurrentEnergy() < 4){
                addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ImpiousBishop();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot(new SFXAction("ImpiousBishop"));
        addToBot(new GainBlockAction(AbstractDungeon.player,this.block));
        addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,3));
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


