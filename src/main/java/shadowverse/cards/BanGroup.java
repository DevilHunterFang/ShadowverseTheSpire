package shadowverse.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class BanGroup {
    public ArrayList<AbstractCard> group;
    public String imgName;

    public BanGroup(String imgName) {
        this.imgName = imgName;
        this.group = new ArrayList<>();
    }

    public void addCard(AbstractCard card) {
        this.group.add(card);
    }
}
