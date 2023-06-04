package shadowverse.orbs;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.MinionAttackAction;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.Neutral.Temp.GildedBoots;
import shadowverse.powers.DualbladePower;

public class Cannoneer extends Minion {

    // Standard ID/Description
    public static final String ORB_ID = "shadowverse:Cannoneer";
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final int ATTACK = 1;
    private static final int DEFENSE = 1;

    public Cannoneer() {
        this.ID = ORB_ID;
        this.img = ImageMaster.loadImage("img/orbs/Cannoneer.png");
        this.name = orbString.NAME;
        this.attack = this.baseAttack = ATTACK;
        this.defense = this.baseDefense = DEFENSE;
        this.updateDescription();
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        description = DESCRIPTIONS[0] + "3*" + this.attack + "=" + 3 * this.attack + DESCRIPTIONS[1];
    }




    @Override
    public AbstractOrb makeCopy() {
        return new Cannoneer();
    }

    @Override
    public void effect() {
        int damage = this.attack * 3;
        if (AbstractDungeon.player.hasPower("Electro")) {
            AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), false));
        }
    }

    @Override
    public void onEndOfTurn() {
        if (this.defense > 0) {
            this.effect();
            if (this.defense == 1){
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new GildedBoots(),1));
            }
            if (AbstractDungeon.player.hasPower(DualbladePower.POWER_ID)&&this.defense>1){
                this.effect();
                AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, this));
            }
            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, this));
            this.updateDescription();
        }
    }

    @Override
    public void onEvoke() {
        super.onEvoke();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new GildedBoots(),1));
    }
}
