package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class YggdrasilPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:YggdrasilPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:YggdrasilPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int turn = 1;

    public YggdrasilPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/YggdrasilPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        StringBuffer sb = new StringBuffer(DESCRIPTIONS[0]);
        switch (this.turn) {
            case 1:
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[2]);
                sb.append(DESCRIPTIONS[3]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[4]);
                sb.append(DESCRIPTIONS[5]);
                break;
            case 2:
                sb.append(DESCRIPTIONS[2]);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[3]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[4]);
                sb.append(DESCRIPTIONS[5]);
                break;
            case 3:
                sb.append(DESCRIPTIONS[2]);
                sb.append(DESCRIPTIONS[3]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[4]);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[5]);
                break;
        }
        this.description = sb.toString();
    }

    public void atStartOfTurnPostDraw() {
        switch (this.turn) {
            case 1:
                addToBot(new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
                addToBot(new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,1),1));
                this.turn++;
                break;
            case 2:
                addToBot((AbstractGameAction) new DrawCardAction(this.amount));
                this.turn++;
                break;
            case 3:
                addToBot((AbstractGameAction) new HealAction(this.owner, this.owner, 3));
                addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner,this.owner,this));
                break;
        }
        updateDescription();
    }
}

