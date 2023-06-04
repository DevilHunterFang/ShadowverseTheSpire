package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import shadowverse.cards.Neutral.Temp.Fairy;


public class WonderTreePower
        extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:WonderTreePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WonderTreePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean hasFusion;
    private int turn = 1;
    private boolean upgraded;

    public WonderTreePower(AbstractCreature owner, int amount, int amount2, boolean hasFusion, boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;
        this.hasFusion = hasFusion;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/WonderTreePower.png");
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
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[3]);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[4]);
                sb.append(this.amount);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[5]);
                if (!this.upgraded)
                    sb.append(DESCRIPTIONS[6]);
                else
                    sb.append(DESCRIPTIONS[7]);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[8]);
                sb.append(this.amount * this.amount2);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[9]);
                sb.append(DESCRIPTIONS[10]);
                break;
            case 2:
                sb.append(DESCRIPTIONS[2]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[3]);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[4]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[5]);
                if (!this.upgraded)
                    sb.append(DESCRIPTIONS[6]);
                else
                    sb.append(DESCRIPTIONS[7]);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[8]);
                sb.append(this.amount * this.amount2);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[9]);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[10]);
                break;
            case 3:
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[2]);
                sb.append(this.amount);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[3]);
                sb.append(DESCRIPTIONS[4]);
                sb.append(this.amount);
                sb.append(DESCRIPTIONS[5]);
                if (!this.upgraded)
                    sb.append(DESCRIPTIONS[6]);
                else
                    sb.append(DESCRIPTIONS[7]);
                sb.append(DESCRIPTIONS[8]);
                sb.append(this.amount * this.amount2);
                sb.append(DESCRIPTIONS[9]);
                if (this.hasFusion)
                    sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[10]);
                break;
        }
        this.description = sb.toString();
    }

    public void atStartOfTurnPostDraw() {
        AbstractCard fairy = new Fairy();
        switch (this.turn) {
            case 1:
                addToBot((AbstractGameAction) new DrawCardAction(this.amount));
                if (this.hasFusion) {
                    if (this.upgraded) {
                        fairy.upgrade();
                    }
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(fairy, this.amount));
                    addToBot((AbstractGameAction) new GainBlockAction(this.owner, this.amount * this.amount2));
                }
                this.turn++;
                break;
            case 2:
                if (this.upgraded) {
                    fairy.upgrade();
                }
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(fairy, this.amount));
                addToBot((AbstractGameAction) new GainBlockAction(this.owner, this.amount * this.amount2));
                if (this.hasFusion) {
                    addToBot((AbstractGameAction) new HealAction(this.owner, this.owner, 2));
                }
                this.turn++;
                break;
            case 3:
                addToBot((AbstractGameAction) new HealAction(this.owner, this.owner, 2));
                if (this.hasFusion) {
                    addToBot((AbstractGameAction) new DrawCardAction(this.amount));
                }
                this.turn = 1;
                break;
        }
        updateDescription();
    }
}

