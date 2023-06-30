package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.WindblastDragon;

public class PolyphonicRoarPower extends AbstractPower implements NonStackablePower {
    public static final String POWER_ID = "shadowverse:PolyphonicRoarPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:PolyphonicRoarPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean upgraded;

    public PolyphonicRoarPower(AbstractCreature owner, int amount, boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.upgraded = upgraded;
        updateDescription();
        loadRegion("carddraw");
    }

    @Override
    public void atStartOfTurn() {
        AbstractCard c = new WindblastDragon();
        c.cost = 0;
        c.setCostForTurn(0);
        if (upgraded)
            c.upgrade();
        addToBot(new MakeTempCardInHandAction(c,this.amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + (upgraded?DESCRIPTIONS[1]:DESCRIPTIONS[2]);
    }
}
