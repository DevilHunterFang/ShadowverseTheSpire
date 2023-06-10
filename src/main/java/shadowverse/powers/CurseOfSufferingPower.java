package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CurseOfSufferingPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:CurseOfSufferingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:CurseOfSufferingPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CurseOfSufferingPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 3;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/AmaryllisPower.png");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            if (this.amount > 0){
                if ((Integer) TempHPField.tempHp.get(this.owner) > 0){
                    addToBot(new AddTemporaryHPAction(this.owner,this.owner,-1));
                }else {
                    addToBot(new DamageAction(this.owner,new DamageInfo(this.owner,1, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                }
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                    if (mo != null && !mo.isDeadOrEscaped()){
                        addToBot(new DamageAction(mo,new DamageInfo(this.owner,1, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
                    }
                }
                this.amount--;
                if (this.amount <= 0){
                    addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
                }
            }
        }
    }
}
