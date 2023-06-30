package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractRoyalSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new PekoSkin(), new ShizuruSkin(), new MonikaSkin() };

    public AbstractRoyalSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
