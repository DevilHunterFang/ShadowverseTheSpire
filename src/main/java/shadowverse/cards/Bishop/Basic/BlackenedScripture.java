package shadowverse.cards.Bishop.Basic;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.characters.Bishop;


public class BlackenedScripture
        extends CustomCard {
    public static final String ID = "shadowverse:BlackenedScripture";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlackenedScripture");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BlackenedScripture.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/BlackenedScripture_L.png");

    public BlackenedScripture() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.BASIC, CardTarget.ENEMY);
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


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && !m.isDeadOrEscaped()) {
            if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
                addToBot(new SFXAction("BlackenedScripture"));
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new MiracleEffect(Color.SKY.cpy(), Color.WHITE.cpy(), "HEAL_3")));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) m, this.magicNumber, false), this.magicNumber));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new FrailPower((AbstractCreature) m, this.magicNumber, false), this.magicNumber));
        }
        addToBot((AbstractGameAction) new JudgementAction((AbstractCreature) m, 6));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BlackenedScripture();
    }
}

