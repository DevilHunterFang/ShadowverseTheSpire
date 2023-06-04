package shadowverse.cards.Vampire.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.ShemyazaPower;


public class Shemyaza extends CustomCard {
    public static final String ID = "shadowverse:Shemyaza";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Shemyaza");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Shemyaza.png";

    public Shemyaza() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);

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
        addToBot((AbstractGameAction)new SFXAction("Shemyaza"));
        if (Settings.FAST_MODE) {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.BLACK, abstractPlayer.hb.cX,abstractPlayer.hb.cY), 0.33F));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new VerticalAuraEffect(Color.GOLD, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.33F));
        if (!abstractPlayer.hasPower("shadowverse:Shemyaza"))
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new ShemyazaPower((AbstractCreature) abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Shemyaza();
    }
}

