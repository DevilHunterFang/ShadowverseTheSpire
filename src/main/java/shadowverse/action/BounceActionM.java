package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;

public class BounceActionM extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;

    private AbstractPlayer p;

    public BounceActionM(int amount) {
        this.p = AbstractDungeon.player;
        setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.discardPile.group) {
                if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK || CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.POWER)
                    tmp.addToRandomSpot(c);
            }
            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.discardPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DISCARD_PILE_X;
                    card.current_y = CardGroup.DISCARD_PILE_Y;
                    this.p.discardPile.removeCard(card);
                    AbstractDungeon.player.hand.addToTop(card);
                    if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                        addToBot(new DrawCardAction(this.p,1));
                    }
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }
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
                if (this.p.hand.size() == 10) {
                    this.p.discardPile.moveToDiscardPile(c);
                    this.p.createHandIsFullDialog();
                } else {
                    this.p.discardPile.removeCard(c);
                    this.p.hand.addToTop(c);
                }
                if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                    addToBot(new DrawCardAction(this.p,1));
                }
                this.p.hand.refreshHandLayout();
                this.p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
