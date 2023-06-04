package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.Due;
import shadowverse.cards.Neutral.Temp.Tre;
import shadowverse.cards.Neutral.Temp.Uno;

import java.util.ArrayList;

public class TreAction extends AbstractGameAction {


    public TreAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }
    @Override
    public void update() {
        ArrayList<AbstractCard> uList = new ArrayList<>();
        ArrayList<AbstractCard> dList = new ArrayList<>();
        int tAmt = 0;
        for (AbstractCard c: AbstractDungeon.player.exhaustPile.group){
            if (c instanceof Uno)
                uList.add(c);
            else if (c instanceof Due)
                dList.add(c);
        }
        tAmt = uList.size()<=dList.size()?uList.size():dList.size();
        if (tAmt==0){
            this.isDone = true;
            return;
        }
        for (int i =0;i<tAmt;i++){
            AbstractDungeon.player.exhaustPile.removeCard(uList.get(i));
            AbstractDungeon.player.exhaustPile.removeCard(dList.get(i));
            AbstractCard tre = (AbstractCard)new Tre();
            tre.setCostForTurn(0);
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(tre,tAmt));
        }
        this.isDone = true;
    }

}
