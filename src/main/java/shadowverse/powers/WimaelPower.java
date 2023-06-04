package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.Elf.Return.Wimael;

public class WimaelPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:WimaelPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WimaelPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

    public WimaelPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/WimaelPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    @Override
    public void atStartOfTurn() {
        for (int i = 0 ; i < this.amount ; i++ ){
            AbstractCard tmp = new Wimael().makeStatEquivalentCopy();
            tmp.setCostForTurn(0);
            tmp.costForTurn = 0;
            tmp.isCostModified = true;
            tmp.exhaustOnUseOnce = true;
            tmp.exhaust = true;
            tmp.rawDescription += " NL " + TEXT + " 。";
            tmp.initializeDescription();
            tmp.applyPowers();
            tmp.lighten(true);
            tmp.setAngle(0.0F);
            tmp.drawScale = 0.12F;
            tmp.targetDrawScale = 0.75F;
            tmp.current_x = Settings.WIDTH / 2.0F;
            tmp.current_y = Settings.HEIGHT / 2.0F;
            AbstractDungeon.player.hand.addToTop(tmp);
        }
        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.hand.applyPowers();
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }
}

