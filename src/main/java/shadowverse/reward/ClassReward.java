package shadowverse.reward;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;

public class ClassReward extends CustomReward {
    public static final String ID = "shadowverse:ClassReward";

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    public CardGroup group;

    public ClassReward(AbstractCard.CardColor color) {
        super(ImageMaster.loadImage("img/reward/placeholder.png"), TEXT[0], RewardType.CARD);
        this.group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.color == color) {
                this.group.addToBottom(q.makeCopy());
            }
        }
        this.cards.clear();
        this.cards.addAll(getCards());
    }

    public ArrayList<AbstractCard> getCards() {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        while (cardsList.size() < 3) {
            AbstractCard card = this.group.getRandomCard(true, AbstractDungeon.getCurrRoom().getCardRarity(AbstractDungeon.cardRandomRng.random(100)));
            if (!cardListDuplicate(cardsList, card)) {
                cardsList.add(card);
            }
        }
        return cardsList;
    }

    public boolean cardListDuplicate(ArrayList<AbstractCard> cardsList, AbstractCard card) {
        for (AbstractCard alreadyHave : cardsList) {
            if (alreadyHave.cardID.equals(card.cardID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[0]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }
}

