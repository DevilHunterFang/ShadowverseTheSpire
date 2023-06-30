package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class ImperialDragoonAction extends AbstractGameAction {
    private int amount;
    public ImperialDragoonAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.DISCARD;
    }

    @Override
    public void update() {
        int theSize = AbstractDungeon.player.hand.size();
        addToTop(new SFXAction("ATTACK_HEAVY"));
        addToTop((new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.2F)));
        addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.amount*theSize, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));
        this.isDone = true;
    }
}
