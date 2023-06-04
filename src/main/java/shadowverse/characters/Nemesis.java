package shadowverse.characters;

import basemod.animations.AbstractAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.cards.Nemesis.Basic.Defend_Nm;
import shadowverse.cards.Nemesis.Basic.DimensionCut;
import shadowverse.cards.Nemesis.Basic.MagisteelLion;
import shadowverse.cards.Nemesis.Basic.Strike_Nm;
import shadowverse.cards.Nemesis.NemesisPool;
import shadowverse.effect.ShadowverseEnergyOrb;
import shadowverse.patch.CharacterSelectScreenPatches;

import java.util.ArrayList;

public class Nemesis extends AbstractShadowversePlayer{
    public static class Enums
    {
        @SpireEnum
        public static PlayerClass Nemesis;
        @SpireEnum(name = "NEMESIS_PURPLE_COLOR")
        public static AbstractCard.CardColor COLOR_SKY;
        @SpireEnum(name = "NEMESIS_PURPLE_COLOR")
        public static CardLibrary.LibraryType TYPE_SKY;


    }

    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Nemesis");

    public static shadowverse.animation.AbstractAnimation bigAnimation = new shadowverse.animation.AbstractAnimation("img/animation/Nemesis/class_808.atlas", "img/animation/Nemesis/class_808.json", com.megacrit.cardcrawl.core.Settings.M_W / 1600.0F, com.megacrit.cardcrawl.core.Settings.M_W / 2.0F, com.megacrit.cardcrawl.core.Settings.M_H / 2.0F, 0F, 0F);

    private static Texture BASE_LAYER = new Texture("img/ui/layer_nemesis.png");

    public Nemesis(String name) {
        super(name, Enums.Nemesis, new ShadowverseEnergyOrb(null, null,null,BASE_LAYER), (AbstractAnimation)new SpriterAnimation(((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).scmlURL));
        initializeClass(null, ((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).SHOULDER1, ((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).SHOULDER2, ((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
        bigAnimation.setVisible(false);
        bigAnimation.skeleton.setSkin("skin_01");
        this.cardPool= new NemesisPool(0);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>(); int i;
        for (i = 0; i < 5; i++) {
            starterDeck.add(Strike_Nm.ID);
        }
        for (i = 0; i < 5; i++) {
            starterDeck.add(Defend_Nm.ID);
        }
        starterDeck.add(DimensionCut.ID);
        starterDeck.add(MagisteelLion.ID);
        return starterDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("shadowverse:Offensive5");
        UnlockTracker.markRelicAsSeen("shadowverse:Offensive5");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 75, 75, 0, 0, 5,
                (AbstractPlayer)this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_SKY;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(18, 108, 146);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new MagisteelLion();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(18, 108, 146);
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
        ((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).playSelect();
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
        return (AbstractPlayer)new Nemesis(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(18, 108, 146);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        ((CharacterSelectScreenPatches.characters[6]).skins[(CharacterSelectScreenPatches.characters[6]).reskinCount]).playHurtSound(lastDamageTaken);
    }
    public static shadowverse.animation.AbstractAnimation getBigAnimation() {
        return bigAnimation;
    }
}
