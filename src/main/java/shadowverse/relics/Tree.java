package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;

public class Tree extends CustomRelic {
    public static final String ID = "shadowverse:Tree";
    public static final String IMG = "img/relics/Tree.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Tree_Outline.png";

    public Tree() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new Tree();
    }

    @Override
    public void atTurnStart() {
        addToBot(new MakeTempCardInHandAction(new NaterranGreatTree()));
    }
}