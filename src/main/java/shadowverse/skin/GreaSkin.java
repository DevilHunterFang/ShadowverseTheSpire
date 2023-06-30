package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class GreaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Dragon");
    private static final String SHOULDER = "img/character/Dragon/shoulder2.png";
    private static final String SKIN_CORPSE = "img/character/Dragon/corpse2.png";
    private static final String SCML = "img/character/Dragon/Grea/images/Grea.scml";
    public static final String SELECTED = "Grea_Select";
    public static final String HURT_SOUND1 = "Grea_Hurt";
    public static final String HURT_SOUND2 = "Grea_Hurt2";
    public static final String HURT_SOUND3 = "Grea_Hurt3";
    public static final String HURT_SOUND4 = "Grea_Hurt3";

    public GreaSkin(){
        this.NAME =  charStrings.NAMES[2];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Dragon/background2.png");
        this.SHOULDER1 = SHOULDER;
        this.SHOULDER2 = SHOULDER;
        this.CORPSE = SKIN_CORPSE;
        this.scmlURL = SCML;
        this.select = SELECTED;
        this.hurt = HURT_SOUND1;
        this.hurt2 = HURT_SOUND2;
        this.hurt3 = HURT_SOUND3;
        this.hurt4 = HURT_SOUND4;
    }
}
