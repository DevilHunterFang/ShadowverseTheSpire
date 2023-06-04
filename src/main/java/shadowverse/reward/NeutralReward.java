package shadowverse.reward;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import shadowverse.cards.AbstractNeutralCard;

import java.util.ArrayList;

public class NeutralReward extends CustomReward {
    public static final String ID = "shadowverse:NeutralReward";

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public NeutralReward() {
        super(ImageMaster.loadImage("img/reward/placeholder.png"),  TEXT[0], RewardType.CARD);
        this.cards.clear();
        this.cards.addAll(getCards());
    }

    public static ArrayList<AbstractCard> getCards() {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        while (cardsList.size() < 3) {
            AbstractCard q = getNeutral();
            if (!cardListDuplicate(cardsList, q))
                cardsList.add(q);
        }
        return cardsList;
    }

    public static AbstractCard getNeutral() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q instanceof AbstractNeutralCard)
                list.add(q.makeCopy());
        }
        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }

    public static boolean cardListDuplicate(ArrayList<AbstractCard> cardsList, AbstractCard card) {
        for (AbstractCard alreadyHave : cardsList) {
            if (alreadyHave.cardID.equals(card.cardID))
                return true;
        }
        return false;
    }

    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, (RewardItem)this, TEXT[0]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }
}
