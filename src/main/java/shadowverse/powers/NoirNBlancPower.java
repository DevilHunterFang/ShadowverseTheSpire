package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

public class NoirNBlancPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:NoirNBlancPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NoirNBlancPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean isFullyOverflow;

    public NoirNBlancPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/NoirNBlancPower.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (this.owner.hasPower(OverflowPower.POWER_ID)) {
                TwoAmountPower p = (TwoAmountPower) this.owner.getPower(OverflowPower.POWER_ID);
                if (p.amount2 > 1) {
                    isFullyOverflow = true;
                }
            }
            if (isFullyOverflow){
                addToBot(new SFXAction("NoirNBlancPower"));
                addToBot(new GainBlockAction(this.owner,this.amount));
                addToBot(new ApplyPowerAction(this.owner,this.owner,new ArtifactPower(this.owner,1),1));
                addToBot(new ApplyPowerAction(this.owner,this.owner,new BlurPower(this.owner,1),1));
                addToBot(new ApplyPowerAction(this.owner,this.owner,new DisableEffectDamagePower(this.owner,1),1));
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        if (isFullyOverflow && this.owner.currentBlock > 0){
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null){
                addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
                addToBot(new DamageAction(m,new DamageInfo(this.owner,this.amount,DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            }
        }
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
