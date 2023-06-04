package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Witch.Chess.MagicalKnight;
import shadowverse.cards.Witch.Chess.MagicalRook;
import shadowverse.cards.Neutral.Temp.MagicalPawn;


public class BlitzPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:BlitzPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:BlitzPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int turn = 1;

    public BlitzPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/CheckmatePower.png");
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
                sb.append(DESCRIPTIONS[4]);
                sb.append(DESCRIPTIONS[5]);
                break;
            case 2:
                sb.append(DESCRIPTIONS[2]);
                sb.append(DESCRIPTIONS[1]);
                sb.append(DESCRIPTIONS[3]);
                sb.append(DESCRIPTIONS[4]);
                sb.append(DESCRIPTIONS[5]);
                break;
            case 3:
                sb.append(DESCRIPTIONS[2]);
                sb.append(DESCRIPTIONS[3]);
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
                addToBot(new MakeTempCardInHandAction(new MagicalPawn(),2));
                this.turn++;
                break;
            case 2:
                AbstractCard tempCard = new MagicalRook();
                if (tempCard.costForTurn > 0) {
                    tempCard.cost = 0;
                    tempCard.costForTurn = 0;
                    tempCard.isCostModifiedForTurn = true;
                }
                tempCard.exhaustOnUseOnce = true;
                tempCard.exhaust = true;
                tempCard.applyPowers();
                tempCard.lighten(true);
                tempCard.setAngle(0.0F);
                tempCard.drawScale = 0.12F;
                tempCard.targetDrawScale = 0.75F;
                tempCard.current_x = Settings.WIDTH / 2.0F;
                tempCard.current_y = Settings.HEIGHT / 2.0F;
                AbstractDungeon.player.hand.addToTop(tempCard);
                this.turn++;
                break;
            case 3:
                AbstractCard tempCard2 = new MagicalKnight();
                if (tempCard2.costForTurn > 0) {
                    tempCard2.cost = 0;
                    tempCard2.costForTurn = 0;
                    tempCard2.isCostModifiedForTurn = true;
                }
                tempCard2.exhaustOnUseOnce = true;
                tempCard2.exhaust = true;
                tempCard2.applyPowers();
                tempCard2.lighten(true);
                tempCard2.setAngle(0.0F);
                tempCard2.drawScale = 0.12F;
                tempCard2.targetDrawScale = 0.75F;
                tempCard2.current_x = Settings.WIDTH / 2.0F;
                tempCard2.current_y = Settings.HEIGHT / 2.0F;
                AbstractDungeon.player.hand.addToTop(tempCard2);
                addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner,this.owner,this));
                break;
        }
        updateDescription();
    }
}

