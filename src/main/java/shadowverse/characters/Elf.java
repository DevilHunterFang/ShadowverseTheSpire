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
import shadowverse.cards.Elf.Basic.Defend_E;
import shadowverse.cards.Elf.Basic.FairyWhisperer;
import shadowverse.cards.Elf.Basic.Strike_E;
import shadowverse.cards.Elf.Basic.SylvanJustice;
import shadowverse.cards.Elf.ElfPool;
import shadowverse.effect.ShadowverseEnergyOrb;
import shadowverse.patch.CharacterSelectScreenPatches;

import java.util.ArrayList;

public class Elf extends AbstractShadowversePlayer{
    public static class Enums
    {
        @SpireEnum
        public static PlayerClass Elf;
        @SpireEnum(name = "ELF_GREEN_COLOR")
        public static AbstractCard.CardColor COLOR_GREEN;
        @SpireEnum(name = "ELF_GREEN_COLOR")
        public static CardLibrary.LibraryType TYPE_GREEN;


    }

    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Elf");

    public static shadowverse.animation.AbstractAnimation bigAnimation = new shadowverse.animation.AbstractAnimation("img/animation/Elf/class_1901.atlas", "img/animation/Elf/class_1901.json", com.megacrit.cardcrawl.core.Settings.M_W / 1600.0F, com.megacrit.cardcrawl.core.Settings.M_W / 2.0F, com.megacrit.cardcrawl.core.Settings.M_H / 2.0F, 0F, 0F);
    private static Texture BASE_LAYER = new Texture("img/ui/layer_elf.png");

    public Elf(String name) {
        super(name, Enums.Elf, new ShadowverseEnergyOrb(null, null,null,BASE_LAYER), (AbstractAnimation)new SpriterAnimation(((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).scmlURL));
        initializeClass(null, ((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).SHOULDER1, ((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).SHOULDER2, ((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
        bigAnimation.setVisible(false);
        this.cardPool= new ElfPool(0);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>(); int i;
        for (i = 0; i < 5; i++) {
            starterDeck.add(Strike_E.ID);
        }
        for (i = 0; i < 5; i++) {
            starterDeck.add(Defend_E.ID);
        }
        starterDeck.add(FairyWhisperer.ID);
        starterDeck.add(SylvanJustice.ID);
        return starterDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("shadowverse:Offensive2");
        UnlockTracker.markRelicAsSeen("shadowverse:Offensive2");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 72, 72, 0, 99, 5,
                (AbstractPlayer)this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_GREEN;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(197, 220, 88);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new SylvanJustice();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(197, 220, 88);
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
        ((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).playSelect();
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
        return (AbstractPlayer)new Elf(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(197, 220, 88);
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
        ((CharacterSelectScreenPatches.characters[1]).skins[(CharacterSelectScreenPatches.characters[1]).reskinCount]).playHurtSound(lastDamageTaken);
    }
    public static shadowverse.animation.AbstractAnimation getBigAnimation() {
        return bigAnimation;
    }
}
