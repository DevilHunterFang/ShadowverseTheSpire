package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ResplendentPhoenixPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:ResplendentPhoenixPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ResplendentPhoenixPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ResplendentPhoenixPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ResplendentPhoenixPower.png");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        int cost = card.cost;
        for (int i = 0 ; i < amount; i++){
            cost = (int) Math.ceil((double)cost /2);
        }
        card.setCostForTurn(cost);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
