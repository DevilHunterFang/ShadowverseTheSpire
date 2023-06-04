package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BanShadowverse extends CustomRelic {
    public static final String ID = "shadowverse:BanShadowverse";
    public static final String IMG = "img/relics/Underdog.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Underdog_Outline.png";

    public BanShadowverse() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new BanShadowverse();
    }

}