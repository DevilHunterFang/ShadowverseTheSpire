package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.DiscardPower;

import java.util.ArrayList;
import java.util.Collections;

public class DiscardRandomMinimumAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    public DiscardRandomMinimumAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.p = (AbstractPlayer) target;
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.duration = DURATION;
    }

    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                int tmp = this.p.hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
                AbstractDungeon.player.hand.applyPowers();
                if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                    ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount++;
                }
                for (AbstractPower power : AbstractDungeon.player.powers){
                    if (power instanceof DiscardPower){
                        ((DiscardPower) power).triggerOnDiscard(tmp);
                    }
                }
                tickDuration();
                return;
            }
            for (int i = 0; i < this.amount; i++) {
                ArrayList<AbstractCard> tmp = new ArrayList<>();
                int min = 99;
                for (AbstractCard c: p.hand.group){
                    if (c.cost < min)
                        min = c.cost;
                }
                for (AbstractCard c: p.hand.group){
                    if (c.cost == min)
                        tmp.add(c);
                }
                Collections.sort(tmp);
                AbstractCard card = tmp.get(AbstractDungeon.cardRandomRng.random(tmp.size() - 1));
                this.p.hand.moveToDiscardPile(card);
                card.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount++;
            }
            for (AbstractPower power : AbstractDungeon.player.powers){
                if (power instanceof DiscardPower){
                    ((DiscardPower) power).triggerOnDiscard(this.amount);
                }
            }
            this.isDone = true;
        }
    }
}
