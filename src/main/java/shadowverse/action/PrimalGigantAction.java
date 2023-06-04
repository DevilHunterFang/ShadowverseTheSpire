package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.DeadSoulTaker;
import shadowverse.cards.Bishop.Amulet2.Jatelant;
import shadowverse.cards.Elf.Left.PrimalGigant;

import java.util.ArrayList;
import java.util.Collections;

public class PrimalGigantAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> tmp = new ArrayList<String>();
    private AbstractPlayer p = AbstractDungeon.player;

    public PrimalGigantAction(){
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c.type == AbstractCard.CardType.ATTACK){
                if (tmp.contains(c.cardID)||c instanceof PrimalGigant||c instanceof DeadSoulTaker || c instanceof Jatelant) {
                    continue;
                }
                tmp.add(c.cardID);
                list.add(c);
            }
        }
        int toAdd = 10-p.hand.size();
        Collections.shuffle(list);
        for (int i =0;i<toAdd;i++){
            if (list.size()>i){
                AbstractCard tempCard = list.get(i).makeSameInstanceOf();
                if (tempCard.costForTurn > 0) {
                    tempCard.cost = 0;
                    tempCard.costForTurn = 0;
                    tempCard.isCostModifiedForTurn = true;
                }
                tempCard.exhaustOnUseOnce = true;
                tempCard.exhaust = true;
                tempCard.applyPowers();
                tempCard.lighten(true);
                tempCard.setAngle(0.0F);
                tempCard.drawScale = 0.12F;
                tempCard.targetDrawScale = 0.75F;
                tempCard.current_x = Settings.WIDTH / 2.0F;
                tempCard.current_y = Settings.HEIGHT / 2.0F;
                p.hand.addToTop(tempCard);
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
