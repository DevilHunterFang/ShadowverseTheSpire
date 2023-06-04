package shadowverse.cards.Necromancer.Necromancy;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.action.BurialAction;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class Cernunnos extends CustomCard {
    public static final String ID = "shadowverse:Cernunnos";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cernunnos");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cernunnos.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Cernunnos_L.png");
    private boolean triggered;

    public Cernunnos() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new BurialAction(1, new DrawCardAction(this.magicNumber)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID)) {
                addToBot(new DrawCardAction(this.magicNumber));
            }
        }
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot(new SFXAction("Cernunnos_L"));
        else
            addToBot(new SFXAction("Cernunnos"));
        if (!this.triggered) {
            this.triggered = true;
            addToBot(new GainEnergyAction(2));
        }
        if (this.costForTurn > 0) {
            addToBot(new ReanimateAction(this.magicNumber));
            if (abstractPlayer instanceof AbstractShadowversePlayer) {
                if (((AbstractShadowversePlayer) abstractPlayer).necromanceCount >= 20) {
                    addToBot(new ReanimateAction(5));
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cernunnos();
    }
}
