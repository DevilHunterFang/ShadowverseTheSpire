package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Status.EvolutionPoint;

public class Offensive6 extends CustomRelic implements BetterClickableRelic<Offensive6> {
    public static final String ID = "shadowverse:Offensive6";
    public static final String IMG = "img/relics/Offensive.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";
    private boolean triggeredThisTurn;

    public Offensive6() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = 3;
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
    public AbstractRelic makeCopy() {
        return new Offensive6();
    }
}
