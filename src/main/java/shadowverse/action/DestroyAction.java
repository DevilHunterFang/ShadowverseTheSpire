package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.powers.Cemetery;

public class DestroyAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;

    private AbstractPlayer p;
    private AbstractGameAction action;

    public DestroyAction(int amount,AbstractGameAction action) {
        this.p = AbstractDungeon.player;
        this.action = action;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.EXHAUST;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.discardPile.group) {
                if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK)
                    tmp.addToRandomSpot(c);
            }
            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                    card.unhover();
                    addToBot(new ExhaustSpecificCardAction(card,this.p.discardPile));
                    if (null!=action){
                        addToBot(action);
                    }
                addToBot(new ApplyPowerAction(p, p, new Cemetery(p, 1), 1));
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
            tickDuration();
            return;
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                addToBot(new ExhaustSpecificCardAction(c,this.p.discardPile));
                if (null!=action){
                    addToBot(action);
                }
                addToBot(new ApplyPowerAction(p, p, new Cemetery(p, 1), 1));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}
