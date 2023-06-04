package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Vampire.Avarice.Archdemon;

public class ArchdemonAction extends AbstractGameAction {
    private AbstractCard card;

    public ArchdemonAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        addToBot((AbstractGameAction)new ReduceCostAction(this.card));
        this.card.applyPowers();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof Archdemon) {
                addToBot((AbstractGameAction)new ReduceCostAction(c));
                c.applyPowers();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof Archdemon) {
                addToBot((AbstractGameAction)new ReduceCostAction(c));
                c.applyPowers();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Archdemon) {
                addToBot((AbstractGameAction)new ReduceCostAction(c));
                c.applyPowers();
            }
        }
        this.isDone = true;
    }
}
