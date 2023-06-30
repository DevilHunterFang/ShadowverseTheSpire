package shadowverse.cards.Dragon.Ramp;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.characters.Dragon;
import shadowverse.powers.ResplendentPhoenixPower;

public class ResplendentPhoenix
        extends CustomCard {
    public static final String ID = "shadowverse:ResplendentPhoenix";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ResplendentPhoenix");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ResplendentPhoenix.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/ResplendentPhoenix_L.png");

    public ResplendentPhoenix() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("ResplendentPhoenix_L"));
        } else {
            addToBot(new SFXAction("ResplendentPhoenix"));
        }
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ResplendentPhoenixPower(abstractPlayer, 1)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ResplendentPhoenix();
    }
}

