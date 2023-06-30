package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

public class AmaryllisPower extends AbstractPower implements OnLoseTempHpPower, NonStackablePower {
    public static final String POWER_ID = "shadowverse:AmaryllisPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AmaryllisPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean isUpgraded;
    private ArrayList<String> abilitiesToTrigger = new ArrayList<String>(){{
        add("a");
        add("b");
        add("c");
    }};

    public AmaryllisPower(AbstractCreature owner, boolean isUpgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.isUpgraded = isUpgraded;
        updateDescription();
        this.img = new Texture("img/powers/AmaryllisPower.png");
    }

    public void updateDescription() {
        StringBuffer des = new StringBuffer(DESCRIPTIONS[3]);
        if (abilitiesToTrigger.contains("a"))
            des.append(DESCRIPTIONS[0]);
        if (abilitiesToTrigger.contains("b"))
            des.append(DESCRIPTIONS[1]);
        if (abilitiesToTrigger.contains("c"))
            des.append(DESCRIPTIONS[2]);
        if (isUpgraded)
            des.append(DESCRIPTIONS[4]);
        this.description = des.toString();
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        if (isUpgraded)
            return damage + 1.0F;
        return damage;
    }


    @Override
    public int onLoseTempHp(DamageInfo damageInfo, int i) {
        int tempHp = TempHPField.tempHp.get(AbstractDungeon.player);
        if (i < tempHp) {
            if (abilitiesToTrigger.size()>0){
                int rand = AbstractDungeon.aiRng.random(0, abilitiesToTrigger.size()-1);
                switch (abilitiesToTrigger.get(rand)) {
                    case "a":
                        addToBot(new SFXAction("AmaryllisPower"));
                        addToBot(new HealAction(this.owner, this.owner, 1));
                        break;
                    case "b":
                        addToBot(new SFXAction("AmaryllisPower"));
                        addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, 1), 1));
                        break;
                    case "c":
                        addToBot(new SFXAction("AmaryllisPower"));
                        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                                addToBot(new SFXAction("spell_boost"));
                                c.flash();
                                addToBot(new ReduceCostAction(c));
                            }
                            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                                c.flash();
                                c.magicNumber = ++c.baseMagicNumber;
                                addToBot(new SFXAction("spell_boost"));
                            }
                        }
                        break;
                }
                abilitiesToTrigger.remove(rand);
            }
        }
        return i;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.player.hasPower(CurseOfSufferingPower.POWER_ID)){
            if (TempHPField.tempHp.get(AbstractDungeon.player) > 1){
                if (abilitiesToTrigger.size()>0){
                    int rand = AbstractDungeon.aiRng.random(0, abilitiesToTrigger.size()-1);
                    switch (abilitiesToTrigger.get(rand)) {
                        case "a":
                            addToBot(new SFXAction("AmaryllisPower"));
                            addToBot(new HealAction(this.owner, this.owner, 1));
                            break;
                        case "b":
                            addToBot(new SFXAction("AmaryllisPower"));
                            addToBot(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, 1), 1));
                            break;
                        case "c":
                            addToBot(new SFXAction("AmaryllisPower"));
                            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                                if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                                    addToBot(new SFXAction("spell_boost"));
                                    c.flash();
                                    addToBot(new ReduceCostAction(c));
                                }
                                if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                                    c.flash();
                                    c.magicNumber = ++c.baseMagicNumber;
                                    addToBot(new SFXAction("spell_boost"));
                                }
                            }
                            break;
                    }
                    abilitiesToTrigger.remove(rand);
                }
            }
        }
    }
}
