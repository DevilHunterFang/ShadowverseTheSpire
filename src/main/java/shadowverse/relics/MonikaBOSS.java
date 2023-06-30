package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.patch.CharacterSelectScreenPatches;

public class MonikaBOSS extends CustomRelic implements BetterClickableRelic<MonikaBOSS> {
    public static final String ID = "shadowverse:MonikaBOSS";
    public static final String IMG = "img/relics/MonikaBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/MonikaBOSS_Outline.png";
    private boolean triggeredThisTurn;

    public MonikaBOSS() {
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
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive6.ID) && (CharacterSelectScreenPatches.characters[3]).reskinCount == 1;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard instanceof EvolutionPoint){
            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(1, 1, true));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MonikaBOSS();
    }
}
