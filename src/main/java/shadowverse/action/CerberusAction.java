package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.Koko;
import shadowverse.cards.Neutral.Temp.Mimi;
import shadowverse.powers.MementoPower;

public class CerberusAction
        extends AbstractGameAction {

    public CerberusAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        addToBot(new MakeTempCardInHandAction(new Mimi()));
        addToBot(new MakeTempCardInHandAction(new Koko()));
        if (AbstractDungeon.player.hasPower(MementoPower.POWER_ID)) {
            addToBot(new MakeTempCardInHandAction(new Mimi()));
            addToBot(new MakeTempCardInHandAction(new Koko()));
        }
        this.isDone = true;
    }
}


