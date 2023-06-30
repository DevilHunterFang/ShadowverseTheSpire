package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import shadowverse.characters.Dragon;

public class PrimeDragonKeeperPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:PrimeDragonKeeperPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:PrimeDragonKeeperPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PrimeDragonKeeperPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/PrimeDragonKeeperPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.cost < 2 && card.color == Dragon.Enums.COLOR_BROWN && card.type == AbstractCard.CardType.ATTACK) {
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null) {
                addToBot(new SFXAction("PrimeDragonKeeperPower"));
                addToBot(new VFXAction(new FireballEffect(this.owner.hb.cX, this.owner.hb.cY, m.hb.cX, m.hb.cY), 0.1F));
                addToBot(new DamageAction(m, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            if (this.owner.hasPower(OverflowPower.POWER_ID)){
                TwoAmountPower p = (TwoAmountPower) this.owner.getPower(OverflowPower.POWER_ID);
                if (p.amount2 > 0){
                    addToBot(new ApplyPowerAction(this.owner,this.owner,new FlameBarrierPower(this.owner,this.amount),this.amount));
                }
            }
        }
    }
}


