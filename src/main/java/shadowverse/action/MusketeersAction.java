package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.powers.AramisPower;

import java.util.ArrayList;

public class MusketeersAction
        extends AbstractGameAction {

    ArrayList<AbstractCard> card = new ArrayList<>();
    private boolean secondTime;
    private boolean enhanced;

    public static ArrayList<AbstractCard> returnChoose() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Aramis());
        list.add(new Porthos());
        list.add(new Athos());
        list.add(new DArtagnan());
        return list;
    }

    private boolean retrieveCard = false;

    public MusketeersAction(boolean secondTime, boolean enhanced) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
        this.card.addAll(returnChoose());
        this.enhanced = enhanced;
        this.secondTime = secondTime;
    }

    public MusketeersAction(boolean secondTime, boolean enhanced, ArrayList<AbstractCard> card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
        this.card = card;
        this.enhanced = enhanced;
        this.secondTime = secondTime;
    }


    public void update() {
        if (this.enhanced) {
            if (this.duration == Settings.ACTION_DUR_FAST) {
                int ep = 0;
                for (AbstractCard ca : AbstractDungeon.player.hand.group){
                    if (ca instanceof EvolutionPoint){
                        ep++;
                    }
                }
                for (AbstractCard c:this.card){
                    if (ep>1){
                        c.upgrade();
                    }
                    if (c instanceof Aramis) {
                        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AramisPower(AbstractDungeon.player, 1, c)));
                    }
                    if (c instanceof DArtagnan){
                        c.baseDamage *= 2;
                        c.applyPowers();
                    }
                    if (AbstractDungeon.player.hand.group.size()<10){
                        AbstractDungeon.player.hand.addToTop(c);
                    }else {
                        AbstractDungeon.player.discardPile.addToTop(c);
                    }
                }
            }
            this.isDone = true;
            tickDuration();
        } else {
            ArrayList<AbstractCard> generatedCards = null;
            if (this.card.size() != 0) {
                generatedCards = this.card;
            }
            if (this.duration == Settings.ACTION_DUR_FAST) {
                AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], false);
                tickDuration();
                return;
            }
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                    }
                    if (disCard instanceof Aramis) {
                        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AramisPower(AbstractDungeon.player, 1, disCard)));
                    }
                    if (this.amount == 1) {
                        if (AbstractDungeon.player.hand.group.size()<10){
                            AbstractDungeon.player.hand.addToTop(disCard);
                        }else {
                            AbstractDungeon.player.discardPile.addToTop(disCard);
                        }
                    }
                    generatedCards.remove(AbstractDungeon.cardRewardScreen.discoveryCard);
                    this.card = generatedCards;
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
            tickDuration();
            if (secondTime) {
                addToBot(new MusketeersAction(false, false,card));
                this.secondTime = false;
            }
        }
    }
}


