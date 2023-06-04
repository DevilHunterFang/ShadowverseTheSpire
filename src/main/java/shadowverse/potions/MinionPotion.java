package shadowverse.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.action.MinionSummonAction;
import shadowverse.orbs.*;

import java.util.ArrayList;

public class MinionPotion extends CustomPotion {
    public static final String POTION_ID = "shadowverse:MinionPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("shadowverse:MinionPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public MinionPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SNECKO, PotionColor.ELIXIR);
        this.labOutlineColor = CardHelper.getColor(152, 156, 1);
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            ArrayList<Minion> minions = new ArrayList<>();
            minions.add(new FrontguardGeneral());
            minions.add(new HeavyKnight());
            minions.add(new Knight());
            minions.add(new Pirate());
            minions.add(new QueenHemera());
            minions.add(new QueenMagnus());
            minions.add(new Quickblader());
            minions.add(new ShieldGuardian());
            minions.add(new SteelcladKnight());
            minions.add(new Viking());
            for (int i = 0; i < this.potency; i++) {
                addToBot(new MinionSummonAction(minions.get(AbstractDungeon.cardRandomRng.random(minions.size() - 1)).makeCopy()));
            }
        }
    }


    @Override
    public int getPotency(int i) {
        return 2;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new MinionPotion();
    }
}
