package shadowverse.cards.Necromancer.LastWords;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.characters.Necromancer;


public class ResentfulScreaming extends CustomCard {
    public static final String ID = "shadowverse:ResentfulScreaming";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ResentfulScreaming");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ResentfulScreaming.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/ResentfulScreaming_L.png");

    public ResentfulScreaming() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = true;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot((AbstractGameAction) new SFXAction("ResentfulScreaming_L"));
        else
            addToBot((AbstractGameAction) new SFXAction("ResentfulScreaming"));
        if (abstractMonster.hasPower("Artifact")) {
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(abstractMonster, abstractPlayer, "Artifact"));
        } else {
            for (AbstractPower pow : abstractMonster.powers) {
                if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower" && !(pow instanceof StrengthPower) && !(pow instanceof DexterityPower)) {
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner, abstractPlayer, pow.ID));
                    break;
                }
            }
        }
        this.addToBot(new ExhaustAction(1, false, true, true));
        ;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ResentfulScreaming();
    }
}

