package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class MonikaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal");
    private static final String SHOULDER = "img/character/Royal/shoulder3.png";
    private static final String SKIN_CORPSE = "img/character/Royal/corpse3.png";
    private static final String SCML = "img/character/Royal/Monika/images/Monika.scml";
    public static final String SELECTED = "Monika_Select";
    public static final String HURT_SOUND1 = "Monika_Hurt";
    public static final String HURT_SOUND2 = "Monika_Hurt2";
    public static final String HURT_SOUND3 = "Monika_Hurt3";
    public static final String HURT_SOUND4 = "Monika_Hurt3";

    public MonikaSkin(){
        this.NAME =  charStrings.NAMES[3];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Royal/background3.png");
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
