package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractDragonSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Dragon")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new KayaSkin(),new GreaSkin(),new InoriSkin() };

    public AbstractDragonSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
