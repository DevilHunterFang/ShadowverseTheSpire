package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import shadowverse.characters.AbstractShadowversePlayer;

public class AerialCraftDamageAction extends AbstractGameAction {
    private DamageInfo info;

    public AerialCraftDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public void update() {
        if (this.duration == 0.5F)
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        tickDuration();
        if (this.isDone) {
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0) {
                for (AbstractCard ca : AbstractDungeon.player.hand.group){
                    if (ca.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                        addToBot((AbstractGameAction)new ReduceCostForTurnAction(ca,1));
                    }
                }
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }
}
