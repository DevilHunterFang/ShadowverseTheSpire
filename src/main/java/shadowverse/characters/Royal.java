package shadowverse.characters;

import basemod.animations.AbstractAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.cards.Basic.*;
import shadowverse.cards.Common.*;
import shadowverse.cards.Rare.Octrice;
import shadowverse.cards.Uncommon.ApostleOfUsurpation;

import java.util.ArrayList;

public class Royal extends AbstractShadowversePlayer {
    public static class Enums {
        @SpireEnum
        public static PlayerClass Royal;
        @SpireEnum(name = "ROYAL_SCARLET_COLOR")
        public static AbstractCard.CardColor COLOR_YELLOW;
        @SpireEnum(name = "ROYAL_SCARLET_COLOR")
        public static CardLibrary.LibraryType TYPE_YELLOW;


    }

    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal");

    public static final int ENERGY_PER_TURN = 3;
    public static final String Royal_SHOULDER_2 = "img/character/Royal/shoulder.png";
    public static final String Royal_SHOULDER_1 = "img/character/Royal/shoulder.png";
    public static final String Royal_CORPSE = "img/character/Royal/corpse.png";
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 150;
    private static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 5;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    public Royal(String name) {
        super(name, Enums.Royal, null, null, null, (AbstractAnimation) new SpriterAnimation("img/character/Royal/images/Royal.scml"));
        initializeClass(null, Royal_SHOULDER_1, Royal_SHOULDER_2, Royal_CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>();
        int i;
        for (i = 0; i < 5; i++) {
            starterDeck.add(Strike_R.ID);
        }
        for (i = 0; i < 5; i++) {
            starterDeck.add(Defend_R.ID);
        }
        starterDeck.add(OathlessKnight.ID);
        starterDeck.add(SageCommander.ID);
        return starterDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("shadowverse:Offensive6");
        UnlockTracker.markRelicAsSeen("shadowverse:Offensive6");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                (AbstractPlayer) this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_YELLOW;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(152, 156, 1);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard) new SageCommander();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(152, 156, 1);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("Royal_Selected", 0.0F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "AUTOMATON_ORB_SPAWN";
    }

    @Override
    public String getLocalizedCharacterName() {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer) new Royal(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(152, 156, 1);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    @Override
    public void damage(DamageInfo info) {
        super.damage(info);
        if (lastDamageTaken > 0) {
            String sound = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (lastDamageTaken >= 20) {
                if (roll < 50) {
                    sound = "Royal_Hurt3";
                } else {
                    sound = "Royal_Hurt4";
                }
            } else {
                if (roll < 33) {
                    sound = "Royal_Hurt";
                } else if (roll < 66) {
                    sound = "Royal_Hurt1";
                } else {
                    sound = "Royal_Hurt2";
                }
            }
            CardCrawlGame.sound.playA(sound, 0.0F);
        }
    }
}
