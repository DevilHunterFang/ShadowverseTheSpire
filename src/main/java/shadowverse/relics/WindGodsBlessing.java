package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.action.MinionBuffAction;
import shadowverse.orbs.Minion;

public class WindGodsBlessing extends CustomRelic {
    public static final String ID = "shadowverse:WindGodsBlessing";
    public static final String IMG = "img/relics/WindGodsBlessing.png";
    public static final String OUTLINE_IMG = "img/relics/outline/WindGodsBlessing_Outline.png";

    public WindGodsBlessing() {
        super("shadowverse:WindGodsBlessing", ImageMaster.loadImage("img/relics/WindGodsBlessing.png"), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == AbstractCard.CardType.ATTACK && !(c.hasTag(AbstractCard.CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        this.counter = rally();
    }

    @Override
    public void onPlayerEndTurn() {
        if (rally() != this.counter) {
            this.counter = rally();
        }
        if (!this.grayscale && this.counter >= 10) {
            addToBot(new MinionBuffAction(1, 1, true));
            flash();
            this.counter = -1;
            this.grayscale = true;
        }
    }


    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new WindGodsBlessing();
    }
}
