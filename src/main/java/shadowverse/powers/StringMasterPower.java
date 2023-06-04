package shadowverse.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.Puppet;


public class StringMasterPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:StringMasterPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:StringMasterPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean used = false;

    public StringMasterPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("carddraw");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount*2+DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw() {
        used = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Puppet&&!used) {
            flash();
            addToBot((AbstractGameAction)new SFXAction("StringMasterPower"));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Puppet(),this.amount*2));
            used = true;
        }
    }

}

