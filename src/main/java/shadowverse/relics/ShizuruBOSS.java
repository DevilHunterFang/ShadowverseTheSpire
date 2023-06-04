package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.patch.CharacterSelectScreenPatches;

public class ShizuruBOSS extends CustomRelic implements BetterClickableRelic<ShizuruBOSS> {
    public static final String ID = "shadowverse:ShizuruBOSS";
    public static final String IMG = "img/relics/ShizuruBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/IriaBOSS_Outline.png";
    private boolean triggeredThisTurn;

    public ShizuruBOSS() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = 2;
        this.triggeredThisTurn = false;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void atTurnStart() {
        this.triggeredThisTurn = false;
    }

    @Override
    public void onEachRightClick() {
        if (!this.grayscale && !this.triggeredThisTurn) {
            this.counter--;
            this.triggeredThisTurn = true;
            flash();
            AbstractCard c = new EvolutionPoint();
            c.upgrade();
            addToBot(new MakeTempCardInHandAction(c, 1));
            addToBot(new GainBlockAction(AbstractDungeon.player,10));
        }
        if (this.counter == 0) {
            flash();
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        for (int i = 0; i < this.counter; i++) {
            AbstractDungeon.player.heal(3);
        }
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
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive6.ID) && (CharacterSelectScreenPatches.characters[3]).reskinCount == 1;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new ShizuruBOSS();
    }
}
