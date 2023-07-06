package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class FafnirPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:FafnirPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FafnirPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FafnirPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/FafnirPower.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            int attack = 0;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
                if (c.type == AbstractCard.CardType.ATTACK)
                    attack++;
            }
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null){
                addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
                int dmg = 20;
                dmg -= attack * 4;
                if (dmg < 0){
                    dmg = 0;
                }
                addToBot(new DamageAction(m,new DamageInfo(this.owner,dmg, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
