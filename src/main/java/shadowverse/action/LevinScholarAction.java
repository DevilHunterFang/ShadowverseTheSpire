package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class LevinScholarAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public LevinScholarAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                list.add(c);
            }
        }
        AbstractCard c1 = null;
        AbstractCard c2 = null;
        if (list != null && !list.isEmpty()) {
            Collections.shuffle(list);
            c1 = list.get(0);
            if (list.size() >= 2) {
                c2 = list.get(1);
            }
        }
        if (c1 != null && p.hand.size() < 10) {
            c1.retain = true;
            this.p.drawPile.moveToHand(c1, this.p.drawPile);
        }
        if (c2 != null && p.hand.size() < 10) {
            c2.retain = true;
            this.p.drawPile.moveToHand(c2, this.p.drawPile);
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
