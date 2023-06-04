package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class SunnyDayEncounterAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public SunnyDayEncounterAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                list.add(c);
            }
        }
        if (list != null && !list.isEmpty()) {
            Collections.shuffle(list);
            this.p.drawPile.moveToHand(list.get(0), this.p.drawPile);
        }
        for (AbstractCard tempCard : p.hand.group) {
            if (tempCard.hasTag(AbstractShadowversePlayer.Enums.NATURAL)) {
                addToBot(new MakeTempCardInHandAction(new NaterranGreatTree(), 1));
                break;
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
