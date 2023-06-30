package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.orbs.Minion;

public class AramisPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:AramisPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AramisPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard card;

    public AramisPower(AbstractCreature owner, int amount, AbstractCard card) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.card = card;
        updateDescription();
        this.img = new Texture("img/powers/AramisPower.png");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount*3 + DESCRIPTIONS[3]
        +this.amount + DESCRIPTIONS[4] + this.amount*3 + DESCRIPTIONS[5];
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb instanceof Minion) {
            addToBot(new SFXAction("AramisPower"));
            ((Minion) orb).buff(this.amount, this.amount);
            this.card.baseDamage += this.amount*3;
            this.card.applyPowers();
            this.card.flash();
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && card!=this.card){
            addToBot(new SFXAction("AramisPower"));
            addToBot(new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
            addToBot(new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,this.amount),this.amount));
            this.card.baseDamage += this.amount*3;
            this.card.applyPowers();
            this.card.flash();
        }
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }
}
