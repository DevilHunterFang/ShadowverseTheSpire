package shadowverse.cards.Vampire.Bat;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.cards.Neutral.Temp.ForestBat;
import shadowverse.characters.Vampire;
import shadowverse.powers.NightVampirePower;


public class NightVampire extends CustomCard {
    public static final String ID = "shadowverse:NightVampire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NightVampire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NightVampire.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/NightVampire_L.png");

    public NightVampire() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new ForestBat();
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());

    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot((AbstractGameAction) new SFXAction("NightVampire_L"));
        }else {
            addToBot((AbstractGameAction) new SFXAction("NightVampire"));
        }
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 2));
        if (!abstractPlayer.hasPower(NightVampirePower.POWER_ID))
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new NightVampirePower((AbstractCreature) abstractPlayer)));
        if (EnergyPanel.getCurrentEnergy()-this.costForTurn>=3){
            addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new DarkfeastBat(),1));
        }
    }


    public AbstractCard makeCopy() {
        return new NightVampire();
    }
}

