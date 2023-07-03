package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ZoePower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:ZoePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ZoePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean triggered;

    public ZoePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.triggered = false;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ZoePower.png");
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (!triggered){
            return 0;
        }
        return  damage;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (!triggered){

            return 0;
        }
        return  damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        triggered = true;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void onVictory(){
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0){
            p.currentHealth+=this.amount;
            p.update();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
