package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.Neutral.Temp.InfernoDragon;


public class DragonHoardPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:DragonHoardPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DragonHoardPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DragonHoardPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("firebreathing");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.amount -= 1;
            if (this.amount == 0) {
                flash();
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                for (AbstractCard c : AbstractDungeon.player.hand.group){
                    if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                        addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                        addToBot(new MakeTempCardInHandAction(new InfernoDragon()));
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group){
                    if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                        addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.drawPile));
                        addToBot(new MakeTempCardInDrawPileAction(new InfernoDragon(),1,true,true));
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group){
                    if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                        addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.discardPile));
                        addToBot(new MakeTempCardInDiscardAction(new InfernoDragon(),1));
                    }
                }
            }
        }
    }

    public void atStartOfTurnPostDraw() {
        this.amount -= 1;
        if (this.amount == 0) {
            flash();
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            for (AbstractCard c : AbstractDungeon.player.hand.group){
                if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    addToBot(new MakeTempCardInHandAction(new InfernoDragon()));
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group){
                if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.drawPile));
                    addToBot(new MakeTempCardInDrawPileAction(new InfernoDragon(),1,true,true));
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group){
                if (CardLibrary.getCard(c.cardID).type == AbstractCard.CardType.ATTACK){
                    addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.discardPile));
                    addToBot(new MakeTempCardInDiscardAction(new InfernoDragon(),1));
                }
            }
        }
        updateDescription();
    }

}

