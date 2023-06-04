package shadowverse.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.action.AmbushAction;
import shadowverse.action.MinionAttackAction;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.Royal.Ambush.Leod;
import shadowverse.effect.AddCardToStasisEffect;
import shadowverse.powers.DualbladePower;

public class AmbushMinion extends Minion{
    public AbstractCard card;
    public boolean ambush = true;
    private boolean guard;
    private AbstractGameEffect stasisStartEffect;

    public AmbushMinion(int atk, int def, AbstractCard card,boolean guard) {
        this.attack=this.baseAttack=atk;
        this.defense=this.baseDefense=def;
        this.card=card;
        this.guard = guard;
        this.stasisStartEffect = (AbstractGameEffect)new AddCardToStasisEffect(card, this, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false);
        AbstractDungeon.effectsQueue.add(this.stasisStartEffect);
    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        if (this.card instanceof Leod && ((Leod) this.card).chosenBranch()==1){
            AbstractDungeon.actionManager.addToBottom(new SFXAction("Leod2Power"));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new CleaveEffect(), 0.2F));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(9, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            this.ambush = false;
        }
    }

    @Override
    public void onEndOfTurn() {
        if (this.ambush){
            AbstractDungeon.actionManager.addToBottom(new AmbushAction(this));
        }else {
            if (this.defense>0){
                if (AbstractDungeon.player.hasPower(DualbladePower.POWER_ID)&&this.defense>1){
                    this.effect();
                    AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, this));
                }
                this.effect();
                AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, this));
                this.updateDescription();
            }
        }
    }

    @Override
    public void playChannelSFX() {
    }

    @Override
    public void effect() {
        if (this.guard){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.attack * 2));
        }else {
            int damage = this.attack * 5;
            if (AbstractDungeon.player.hasPower("Electro")) {
                AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), true));
            } else {
                AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL), false));
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (this.stasisStartEffect == null || this.stasisStartEffect.isDone) {
            this.card.target_x = this.tX;
            this.card.target_y = this.tY;
            this.card.applyPowers();
            if (this.hb.hovered) {
                this.card.targetDrawScale = 1.0F;
            } else {
                this.card.targetDrawScale = Float.valueOf(0.2F);
            }
        }
        this.card.update();
    }

    @Override
    public void updateDescription() {
    }

    @Override
    public void render(SpriteBatch sb) {
        this.card.render(sb);
        if (this.ambush){
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, Color.GRAY, this.fontScale);
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.attack), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, Color.GRAY, this.fontScale);
        }else {
            if (this.defense > this.baseDefense) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 0.2F, this.c.a), this.fontScale);
            } else if (this.defense < this.baseDefense) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 0.2F, 0.2F, this.c.a), this.fontScale);
            } else {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 1.0F, 1.0F, this.c.a), this.fontScale);
            }
            if (this.attack > this.baseAttack) {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.attack), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 0.2F, this.c.a), this.fontScale);
            } else {
                FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.attack), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 1.0F, 1.0F, this.c.a), this.fontScale);
            }
        }
        hb.render(sb);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new AmbushMinion(this.attack,this.defense,this.card,this.guard);
    }
}
