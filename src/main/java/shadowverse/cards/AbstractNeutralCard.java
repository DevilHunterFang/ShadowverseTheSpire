package shadowverse.cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractNeutralCard extends CustomCard {
    public AbstractNeutralCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
}
