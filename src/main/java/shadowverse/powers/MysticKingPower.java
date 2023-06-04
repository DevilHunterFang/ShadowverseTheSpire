package shadowverse.powers;


import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;


public class MysticKingPower
        extends AbstractPower implements OnLoseTempHpPower {
    public static final String POWER_ID = "shadowverse:MysticKingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MysticKingPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MysticKingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/MysticKingPower.png");
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
    public int onLoseTempHp(DamageInfo damageInfo, int i) {
        int temporaryHealth = ((Integer) TempHPField.tempHp.get(this.owner)).intValue();
        if (i > temporaryHealth) {
            this.owner.isDead = true;
            AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
            this.owner.currentHealth = 0;
            if (this.owner.currentBlock > 0) {
                this.owner.loseBlock();
                AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.owner.hb.cX - this.owner.hb.width / 2.0F + (-14.0F * Settings.scale), this.owner.hb.cY - this.owner.hb.height / 2.0F + (-14.0F * Settings.scale)));
            }
        }
        return i;
    }
}


