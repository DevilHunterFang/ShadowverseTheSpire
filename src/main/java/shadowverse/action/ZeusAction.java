package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.powers.EarthEssence;

public class ZeusAction extends AbstractGameAction {
    public int[] multiDamage;

    private boolean freeToPlayOnce = false;

    private DamageInfo.DamageType damageType;

    private int block;

    private AbstractPlayer p;

    private int energyOnUse = -1;

    public ZeusAction(AbstractPlayer p, int[] multiDamage,int block, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.block = block;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void rand(int[] l, int n, int m)
    {
        int i;
        for(i=0;i<n-1;i++)
        {
            l[i] = AbstractDungeon.cardRandomRng.random(2 * m / (n - i));
            m -= l[i];
        }
        l[i] = m;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            int [] l = new int[5];
            rand(l,5,effect);
            int x = l[0];
            int y = l[1];
            int z = l[2];
            int a = l[3];
            int b = l[4];
            addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, x*5));
            if (y > 0){
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()){
                        addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
                        addToBot(new RemoveAllBlockAction(mo, this.p));
                    }
                }
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, y*3), y*3));
            }
            if (z>0){
                addToBot(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
                for (int i = 0; i < z; i++){
                    addToBot(new SFXAction("THUNDERCLAP", 0.05F));
                    addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.BLUNT_HEAVY, true));
                }
            }
            if (a>0){
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()){
                        addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, a*2, false), a*2));
                        addToBot(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, a*4)));
                    }
                }
            }
            addToBot(new GainBlockAction(p, p, this.block*b));
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
