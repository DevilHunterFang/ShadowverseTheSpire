package shadowverse.orbs;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import shadowverse.action.ReturnAmuletToDiscardAction;
import shadowverse.action.StasisEvokeIfRoomInHandAction;
import shadowverse.cardmods.GarudaMod;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.Bishop.Amulet1.GoldenCity;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.effect.AddCardToStasisEffect;
import shadowverse.powers.UnerielPower;
import shadowverse.relics.ErisRelic;

public class AmuletOrb extends AbstractOrb {

    public static final String ID = "shadowverse:Amulet";

    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ID);

    public static final String[] DESC = orbString.DESCRIPTION;

    public AbstractCard amulet;

    private AbstractGameEffect stasisStartEffect;

    public AmuletOrb(AbstractCard card) {
        this(card,  null);
    }

    public AmuletOrb(AbstractCard card, CardGroup source) {
        this(card, source, false);
    }

    public AmuletOrb(AbstractCard card, CardGroup source, boolean selfStasis) {
        card.targetAngle = 0.0F;
        this.amulet = card;
        this.amulet.beginGlowing();
        if (card instanceof AbstractNoCountDownAmulet){
            this.name = this.amulet.name;
        }else {
            this.name = orbString.NAME + this.amulet.name;
        }
        this.channelAnimTimer = 0.5F;

        if (card instanceof AbstractAmuletCard) {
            this.basePassiveAmount = ((AbstractAmuletCard) card).countDown;
        }else if (card instanceof AbstractCrystalizeCard){
            this.basePassiveAmount = ((AbstractCrystalizeCard)card).returnCountDown();
        } else if(card.type == AbstractCard.CardType.CURSE){
            this.basePassiveAmount = 3;
        }else
            this.basePassiveAmount = 3;
        this.passiveAmount = this.basePassiveAmount;
        this.baseEvokeAmount = this.basePassiveAmount;
        this.evokeAmount = this.baseEvokeAmount;
        updateDescription();
        initialize(source, selfStasis);
    }

    public void applyFocus() {
    }

    public void updateDescription() {
        applyFocus();
        if (!(this.amulet instanceof AbstractNoCountDownAmulet)){
            this.description = DESC[0] + this.passiveAmount + DESC[1] + this.amulet.name;
        }else {
            this.description = this.amulet.name;
        }
    }


    @Override
    public void onEndOfTurn() {
        if (this.amulet instanceof AbstractAmuletCard){
            ((AbstractAmuletCard) this.amulet).endOfTurn(this);
        }
        if (this.amulet instanceof AbstractCrystalizeCard){
            ((AbstractCrystalizeCard)this.amulet).endOfTurn(this);
        }
        if (this.amulet instanceof AbstractNoCountDownAmulet){
            ((AbstractNoCountDownAmulet)this.amulet).endOfTurn(this);
        }
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        if (this.amulet instanceof AbstractAmuletCard) {
            this.amulet.applyPowers();
            ((AbstractAmuletCard) this.amulet).onStartOfTurn(this);
        }
        if (this.amulet instanceof AbstractCrystalizeCard){
            ((AbstractCrystalizeCard)this.amulet).onStartOfTurn(this);
        }
        if (this.amulet instanceof AbstractNoCountDownAmulet){
            ((AbstractNoCountDownAmulet) this.amulet).onStartOfTurn(this);
        }
        if (this.passiveAmount > 0) {
            this.passiveAmount--;
            this.evokeAmount--;
            updateDescription();
        }
        if (this.passiveAmount <= 0 && !(this.amulet instanceof AbstractNoCountDownAmulet)){
            AbstractDungeon.actionManager.addToTop( new StasisEvokeIfRoomInHandAction(this));
            if (AbstractDungeon.player.hasPower(UnerielPower.POWER_ID)){
                AbstractPower uPower = AbstractDungeon.player.getPower(UnerielPower.POWER_ID);
                for (int i = 0; i < uPower.amount; i++){
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("UnerielPower2"));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(((TwoAmountPower)uPower).amount2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
                }
            }
        }
    }

    public void onEvoke() {
        if ((this.amulet instanceof AbstractAmuletCard || this.amulet instanceof AbstractCrystalizeCard) && !this.amulet.hasTag(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE))
            AbstractDungeon.actionManager.addToTop(new ReturnAmuletToDiscardAction(this.amulet));
        if (this.passiveAmount <= 0 && !(this.amulet instanceof AbstractNoCountDownAmulet)) {
            if (this.amulet instanceof AbstractAmuletCard){
                ((AbstractAmuletCard) this.amulet).onEvoke(this);
                if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                    ((AbstractShadowversePlayer)AbstractDungeon.player).amuletCount++;
                }
                if (AbstractDungeon.player.hasRelic(ErisRelic.ID)){
                    AbstractDungeon.actionManager.addToBottom((new AddTemporaryHPAction(AbstractDungeon.player,AbstractDungeon.player,2)));
                }
                this.amulet.superFlash(Color.GOLDENROD);
            }
            if (this.amulet instanceof AbstractCrystalizeCard){
                ((AbstractCrystalizeCard) this.amulet).onEvoke(this);
                if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                    ((AbstractShadowversePlayer)AbstractDungeon.player).amuletCount++;
                }
                if (AbstractDungeon.player.hasRelic(ErisRelic.ID)){
                    AbstractDungeon.actionManager.addToBottom((new AddTemporaryHPAction(AbstractDungeon.player,AbstractDungeon.player,2)));
                }
                this.amulet.superFlash(Color.GOLDENROD);
            }
        }
    }

    public void triggerEvokeAnimation() {
    }

    private void initialize(CardGroup source, boolean selfStasis) {
        if (source != null) {
            source.removeCard(this.amulet);
            switch (source.type) {
                case HAND:
                    this.stasisStartEffect = new AddCardToStasisEffect(this.amulet, this, this.amulet.current_x, this.amulet.current_y, !selfStasis);
                    break;
                case DRAW_PILE:
                    this.stasisStartEffect = new AddCardToStasisEffect(this.amulet, this, AbstractDungeon.overlayMenu.combatDeckPanel.current_x + 100.0F * Settings.scale, AbstractDungeon.overlayMenu.combatDeckPanel.current_y + 100.0F * Settings.scale, !selfStasis);
                    break;
                case DISCARD_PILE:
                    this.stasisStartEffect = new AddCardToStasisEffect(this.amulet, this, AbstractDungeon.overlayMenu.discardPilePanel.current_x - 100.0F * Settings.scale, AbstractDungeon.overlayMenu.discardPilePanel.current_y + 100.0F * Settings.scale, !selfStasis);
                    break;
                case EXHAUST_PILE:
                    this.amulet.unfadeOut();
                    this.stasisStartEffect = new AddCardToStasisEffect(this.amulet, this, AbstractDungeon.overlayMenu.discardPilePanel.current_x - 100.0F * Settings.scale, AbstractDungeon.overlayMenu.exhaustPanel.current_y + 100.0F * Settings.scale, !selfStasis);
                    break;
                default:
                    this.stasisStartEffect = new AddCardToStasisEffect(this.amulet, this, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, !selfStasis);
                    break;
            }
        } else {
            this.stasisStartEffect = new AddCardToStasisEffect(this.amulet.makeStatEquivalentCopy(), this, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, !selfStasis);
        }
        AbstractDungeon.effectsQueue.add(this.stasisStartEffect);
        this.amulet.retain = false;
        if (!(this.amulet instanceof GoldenCity)){
            for (AbstractOrb o:AbstractDungeon.player.orbs){
                if (o instanceof AmuletOrb){
                    if (((AmuletOrb) o).amulet instanceof GoldenCity && this.passiveAmount > 0){
                        this.onStartOfTurn();
                    }
                }
            }
            if (AbstractDungeon.player.hasPower(UnerielPower.POWER_ID)){
                AbstractPower uPower = AbstractDungeon.player.getPower(UnerielPower.POWER_ID);
                for (int i = 0; i < uPower.amount; i++){
                    if (this.passiveAmount > 0){
                        this.onStartOfTurn();
                        AbstractDungeon.actionManager.addToBottom(new SFXAction("UnerielPower"));
                    }
                }
            }
            if (CardModifierManager.hasModifier(((AbstractCard) this.amulet), GarudaMod.ID)){
                this.onStartOfTurn();
            }
        }
    }

    public void update() {
        super.update();
        if (this.stasisStartEffect == null || this.stasisStartEffect.isDone) {
            this.amulet.target_x = this.tX;
            this.amulet.target_y = this.tY;
            this.amulet.applyPowers();
            if (this.hb.hovered) {
                this.amulet.targetDrawScale = 1.0F;
            } else {
                this.amulet.targetDrawScale = Float.valueOf(0.2F);
            }
        }
        this.amulet.update();
    }

    public void render(SpriteBatch sb) {
        if (!this.hb.hovered && (this.stasisStartEffect == null || this.stasisStartEffect.isDone))
            renderActual(sb);
    }

    public void renderActual(SpriteBatch sb) {
        this.amulet.render(sb);
        if (!this.hb.hovered && !(this.amulet instanceof AbstractNoCountDownAmulet)){
            renderText(sb);
        }else if (this.amulet.hasTag(AbstractShadowversePlayer.Enums.MINION)){
            if (this.amulet.block > this.amulet.baseBlock) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amulet.block), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 0.2F, this.c.a), this.fontScale);
            } else if (this.amulet.block < this.amulet.baseBlock) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amulet.block), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 0.2F, 0.2F, this.c.a), this.fontScale);
            } else {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amulet.block), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 1.0F, 1.0F, this.c.a), this.fontScale);
            }
            if (this.amulet.damage > this.amulet.baseDamage) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amulet.damage), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 0.2F, this.c.a), this.fontScale);
            } else {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amulet.damage), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 1.0F, 1.0F, this.c.a), this.fontScale);
            }
        }
        this.hb.render(sb);
    }

    public void renderPreview(SpriteBatch sb) {
        if (this.hb.hovered && (this.stasisStartEffect == null || this.stasisStartEffect.isDone))
            renderActual(sb);
    }

    public void playChannelSFX() {}

    public AbstractOrb makeCopy() {
        AmuletOrb ao = new AmuletOrb(this.amulet);
        ao.passiveAmount = ao.basePassiveAmount = this.passiveAmount;
        ao.evokeAmount = ao.baseEvokeAmount = this.evokeAmount;
        return ao;
    }
}
