package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;


public class RomeliaPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:RomeliaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:RomeliaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RomeliaPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/RomeliaPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    

    public void atStartOfTurnPostDraw() {
        this.amount -= 1;
        addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 2), 2));
        addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseDexterityPower(this.owner, 2), 2));
        addToBot(new GainEnergyAction(1));
        if (this.amount == 0) {
            flash();
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        updateDescription();
    }
}

