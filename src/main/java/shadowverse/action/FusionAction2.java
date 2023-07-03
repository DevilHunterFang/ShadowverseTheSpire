package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class FusionAction2 extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p;

    private boolean anyNumber;

    private boolean canPickZero;

    private boolean isRandom;

    public static int numExhausted;

    private AbstractCard self;

    private ArrayList<AbstractCard> cannotFusion = new ArrayList<>();

    private AbstractCard.CardColor color;

    private boolean hasFusion;
    private AbstractCard.CardType type;
    private Integer cost;

    public FusionAction2(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, AbstractCard self, boolean hasFusion, AbstractCard.CardColor color, AbstractCard.CardType type, Integer cost) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.isRandom = isRandom;
        this.canPickZero = canPickZero;
        this.amount = amount;
        this.hasFusion = hasFusion;
        this.self = self;
        this.color = color;
        this.type = type;
        this.cost = cost;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (hasFusion) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            for (AbstractCard c : this.p.hand.group) {
                if (c.color != color || CardLibrary.getCard(c.cardID).type != type) {
                    this.cannotFusion.add(c);
                }
                if (cost != null) {
                    if (c.cost > cost && !c.isCostModified) {
                        this.cannotFusion.add(c);
                    }
                }
            }
            this.cannotFusion.add(self);
            if (this.cannotFusion.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber &&
                    this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numExhausted = this.amount;
                int tmp = this.p.hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);
                }
                return;
            }
            this.p.hand.group.removeAll(this.cannotFusion);
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++)
                    this.p.hand.moveToExhaustPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
                CardCrawlGame.dungeon.checkForPactAchievement();
            } else {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int count = 0;
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.size() != 0)
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    this.p.hand.moveToExhaustPile(c);
                    count++;
                }
            self.magicNumber += count;
            self.applyPowers();
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotFusion)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }
}
