package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Status.EvolutionPoint;

public class OliviasBlessing extends CustomRelic implements BetterClickableRelic<OliviasBlessing> {
    public static final String ID = "shadowverse:OliviasBlessing";
    public static final String IMG = "img/relics/OliviasBlessing.png";
    public static final String OUTLINE_IMG = "img/relics/outline/OliviasBlessing_Outline.png";

    public OliviasBlessing() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = 3;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onEachRightClick() {
        if (!this.grayscale) {
            this.counter--;
            flash();
            AbstractCard c = new Miracle();
            addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), 1));
            c = new EvolutionPoint();
            c.upgrade();
            addToBot(new MakeTempCardInHandAction(c, 1));
        }
        if (this.counter == 0) {
            flash();
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Offensive6.ID)) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }
    @Override
    public boolean canSpawn(){
        return AbstractDungeon.player.hasRelic(Offensive6.ID);
    }



    @Override
    public AbstractRelic makeCopy() {
        return new OliviasBlessing();
    }
}
