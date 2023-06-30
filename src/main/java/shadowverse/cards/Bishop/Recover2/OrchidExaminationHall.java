package shadowverse.cards.Bishop.Recover2;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.CutthroatPower;

import java.util.ArrayList;

public class OrchidExaminationHall extends AbstractAmuletCard {
    public static final String ID = "shadowverse:OrchidExaminationHall";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OrchidExaminationHall");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OrchidExaminationHall.png";
    private float rotationTimer;
    private int previewIndex;

    public int enhanceCost;
    public int baseCost;

    public boolean exFreeOnce;

    public int exCost;

    public int exCostForTurn;

    public int ex;

    public OrchidExaminationHall() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.countDown = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
        this.baseCost = cost;
        this.enhanceCost = 1;
        this.exCost = cost;
        this.exCostForTurn = cost;
        this.exFreeOnce = false;
        this.ex = 0;
    }

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new BambeautyExaminationHall());
        list.add(new MumExaminationHall());
        list.add(new PlumExaminationHall());
        return list;
    }

    public void update() {
        super.update();
        if (this.hb.hovered){
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT
        ) {
            if (Shadowverse.Enhance(enhanceCost)) {
                if (this.ex == 0) {
                    this.exCost = this.cost;
                    this.exCostForTurn = this.costForTurn;
                }
                this.ex = 1;
                this.exFreeOnce = this.freeToPlayOnce;
                setCostForTurn(enhanceCost);
            } else {
                if (this.ex > 0) {
                    setCostForTurn(baseCost);
                    this.cost = this.exCost;
                    this.costForTurn = this.exCostForTurn;
                    this.freeToPlayOnce = this.exFreeOnce;
                }
                this.ex = 0;
            }
        }
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractEnhanceCard c = (AbstractEnhanceCard) super.makeStatEquivalentCopy();
        c.exCost = this.exCost;
        c.exCostForTurn = this.exCostForTurn;
        c.exFreeOnce = this.exFreeOnce;
        c.ex = this.ex;
        return c;
    }

    public void triggerOnGlowCheck() {
        if (Shadowverse.Enhance(enhanceCost)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
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
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void enhanceUse(AbstractPlayer p, AbstractMonster m){
        ArrayList<AbstractCard> list = returnChoice();
        if (upgraded) {
            for (AbstractCard c : list) {
                c.upgrade();
            }
        }
        addToBot(new ChooseOneAction(list));
    }

    public void baseUse(AbstractPlayer p, AbstractMonster m){
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.costForTurn == enhanceCost && Shadowverse.Enhance(enhanceCost)) {
            enhanceUse(p, m);
            if (p.hasPower(CutthroatPower.POWER_ID)) {
                addToBot(new GainEnergyAction(enhanceCost - 1));
            }
        } else {
            baseUse(p, m);
        }
    }
}
