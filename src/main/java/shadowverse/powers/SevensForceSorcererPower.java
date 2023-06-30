package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class SevensForceSorcererPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:SevensForceSorcererPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:SevensForceSorcererPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SevensForceSorcererPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 7;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SevensForceSorcererPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type== AbstractCard.CardType.SKILL)
        this.amount--;
        if (this.amount == 0) {
            flash();
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
            addToBot((AbstractGameAction)new SFXAction("SevensForceSorcererEff"));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)mo, 99999));
                }
            }
        }
        updateDescription();
    }

    public void atStartOfTurn() {
        this.amount = 7;
        updateDescription();
    }

}
