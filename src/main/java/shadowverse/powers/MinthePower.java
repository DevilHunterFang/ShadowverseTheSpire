package shadowverse.powers;


import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class MinthePower
        extends AbstractPower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = "shadowverse:MinthePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MinthePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MinthePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/MinthePower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount * 20 + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Cemetery(AbstractDungeon.player, -20 * this.amount), -20 * this.amount));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:MinthePower"));
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }

    @Override
    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power instanceof Cemetery && stackAmount < 0){
            flash();
            addToBot(new ApplyPowerAction(target,source,new Cemetery(target,-stackAmount),-stackAmount));
        }
        return stackAmount;
    }
}


