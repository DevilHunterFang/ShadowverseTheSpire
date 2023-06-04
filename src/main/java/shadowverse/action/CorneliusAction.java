package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.Collections;

public class CorneliusAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> tmp = new ArrayList<String>();
    private AbstractPlayer p = AbstractDungeon.player;

    public CorneliusAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c: CardLibrary.getAllCards()){
            if (c.type == AbstractCard.CardType.ATTACK && c.color != Necromancer.Enums.COLOR_PURPLE && c.cost == 1){
                if (tmp.contains(c.cardID)||c instanceof AbstractEnhanceCard) {
                    continue;
                }
                tmp.add(c.cardID);
                list.add(c);
            }
        }
        int toAdd = 4;
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
