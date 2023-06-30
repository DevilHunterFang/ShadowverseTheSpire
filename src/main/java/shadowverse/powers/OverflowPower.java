package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class OverflowPower extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:OverflowPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OverflowPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public OverflowPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = 0;
        this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
        this.img = new Texture("img/powers/OverflowPower.png");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 3 && this.amount2 < 2){
            this.amount2 += 1;
            this.amount = 0;
        }
    }

    public void updateDescription() {
        switch (this.amount2){
            case 0:
                this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
                break;
            case 1:
                this.name = DESCRIPTIONS[5];
                this.description = DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
                break;
            case 2:
                this.name = DESCRIPTIONS[6];
                this.description = DESCRIPTIONS[4];
                break;
        }
    }

    @Override
    public void atStartOfTurn() {
        if (amount2 > 0){
            addToBot(new GainEnergyAction(this.amount2));
        }
    }
}
