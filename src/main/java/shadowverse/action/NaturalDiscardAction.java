package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.powers.DiscardPower;

public class NaturalDiscardAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p;

    private boolean isRandom;

    private boolean endTurn;

    public static int numDiscarded;

    private static final float DURATION = Settings.ACTION_DUR_XFAST;

    private AbstractGameAction action;

    public NaturalDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(target, source, amount, isRandom, false,null);
    }

    public NaturalDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn, AbstractGameAction action) {
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
        this.action = action;
    }

    public void update() {
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount++;
            }
            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                int tmp = this.p.hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToDiscardPile(c);
                    if (c.hasTag(AbstractShadowversePlayer.Enums.NATURAL)){
                        if (action != null){
                            addToBot(action);
                        }
                    }
                    if (!this.endTurn)
                        c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(this.endTurn);
                }
                for (AbstractPower power : AbstractDungeon.player.powers){
                    if (power instanceof DiscardPower){
                        ((DiscardPower) power).triggerOnDiscard(tmp);
                    }
                }
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
            for (AbstractPower power : AbstractDungeon.player.powers){
                if (power instanceof DiscardPower){
                    ((DiscardPower) power).triggerOnDiscard(amount);
                }
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    this.p.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(this.endTurn);
                }
            } else {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    tickDuration();
                    return;
                }
                numDiscarded = this.amount;
                if (this.p.hand.size() > this.amount)
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                if (c.hasTag(AbstractShadowversePlayer.Enums.NATURAL)){
                    if (action != null){
                        addToBot(action);
                    }
                }
                GameActionManager.incrementDiscard(this.endTurn);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
