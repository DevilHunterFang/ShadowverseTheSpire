package shadowverse.cards.Dragon.Buff;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.characters.Dragon;
import shadowverse.powers.RomeliaPower;


public class Romelia extends CustomCard {
    public static final String ID = "shadowverse:Romelia";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Romelia");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Romelia.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Romelia_L.png");

    public Romelia() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.exhaust = true;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("Romelia_L"));
        }else {
            addToBot(new SFXAction("Romelia"));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 2), 2));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new LoseDexterityPower(abstractPlayer, 2), 2));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new RomeliaPower(abstractPlayer, 2), 2));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Romelia();
    }
}

