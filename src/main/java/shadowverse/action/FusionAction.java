package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import shadowverse.cards.Neutral.Neutral.Machinus;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

public class FusionAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p;


    private boolean anyNumber;

    private boolean canPickZero;

    private boolean isRandom;

    public static int numExhausted;

    private AbstractCard.CardTags tag;
    private AbstractCard.CardTags tag2;
    private AbstractCard card;

    private ArrayList<AbstractCard> cannotFusion = new ArrayList<>();

    public FusionAction(int amount, boolean isRandom,boolean anyNumber, boolean canPickZero, AbstractCard card,AbstractCard.CardTags tag, AbstractCard.CardTags tag2) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.isRandom = isRandom;
        this.canPickZero = canPickZero;
        this.amount = amount;
        this.card = card;
        this.tag = tag;
        this.tag2 = tag2;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.EXHAUST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            for (AbstractCard c : this.p.hand.group) {
                if (!c.hasTag(tag)&&!c.hasTag(tag2)) {
                    this.cannotFusion.add(c);
                }
            }
            this.cannotFusion.add(card);
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
                CardCrawlGame.dungeon.checkForPactAchievement();
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
            boolean mCheck = false;
            boolean naCheck = false;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group){
                this.p.hand.moveToExhaustPile(c);
                if (c.hasTag(AbstractShadowversePlayer.Enums.NATURAL)&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&!mCheck&&!naCheck){
                    ((Machinus)card).naturalCheck = true;
                    ((Machinus)card).machineCheck = true;
                    naCheck = true;
                    mCheck = true;
                }else if (c.hasTag(AbstractShadowversePlayer.Enums.NATURAL)&&!naCheck){
                    ((Machinus)card).naturalCheck = true;
                    naCheck = true;
                } else if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&!mCheck) {
                    ((Machinus)card).machineCheck = true;
                    mCheck = true;
                }
                count++;
            }
            if (count != 0){
                ((Machinus)card).fusionCheck = true;
            }
            card.baseMagicNumber += count;
            card.magicNumber = card.baseMagicNumber;
            card.applyPowers();
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotFusion)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }
}
