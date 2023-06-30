package shadowverse.patch;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

import javassist.CtBehavior;
import shadowverse.Shadowverse;
import shadowverse.skin.*;

public class CharacterSelectScreenPatches {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("shadowverse:Skin");

    public static final String[] TEXT = uiStrings.TEXT;

    public static final String[] EXTRA_TEXT = uiStrings.EXTRA_TEXT;

    public static Hitbox reskinRight;

    public static Hitbox reskinLeft;

    public static Hitbox reskinLock;

    public static Texture CF_RIGHT_ARROW = ImageMaster.CF_RIGHT_ARROW;

    public static Texture CF_LEFT_ARROW = ImageMaster.CF_LEFT_ARROW;

    private static float reskin_Text_W = 50.0F * Settings.scale;

    private static float reskin_W = reskin_Text_W + 200.0F * Settings.scale;

    private static float reskinX_center = 630.0F * Settings.scale;

    public static float allTextInfoX = 0.0F;

    private static boolean bgIMGUpdate = false;

    private static float buttonScale = 0.5F;

    private static float buttonY = 950.0F;

    public static ArrayList<AbstractGameEffect> char_effectsQueue = new ArrayList<>();

    public static ArrayList<AbstractGameEffect> char_effectsQueue_toRemove = new ArrayList<>();

    public static AbstractSkinCharacter[] characters = new AbstractSkinCharacter[]{
             new AbstractWitchSkin(),
             new AbstractElfSkin(),
             new AbstractNecroSkin(),
             new AbstractRoyalSkin(),
             new AbstractVampireSkin(),
             new AbstractBishopSkin(),
             new AbstractNemesisSkin(),
             new AbstractDragonSkin()
    };

    public static Color BLACK_OUTLINE_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);

    @SpirePatch(clz = CharacterSelectScreen.class, method = "initialize")
    public static class CharacterSelectScreenPatch_Initialize {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance) {
            Shadowverse.loadSettings();
            CharacterSelectScreenPatches.char_effectsQueue.clear();
            CharacterSelectScreenPatches.reskinRight = new Hitbox(138.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale, 102.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale);
            CharacterSelectScreenPatches.reskinLeft = new Hitbox(138.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale, 102.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale);
            CharacterSelectScreenPatches.reskinLock = new Hitbox(138.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale, 102.0F * Settings.scale * CharacterSelectScreenPatches.buttonScale);
            CharacterSelectScreenPatches.reskinRight.move(Settings.WIDTH / 2.0F + CharacterSelectScreenPatches.reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale);
            CharacterSelectScreenPatches.reskinLeft.move(Settings.WIDTH / 2.0F - CharacterSelectScreenPatches.reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale);
            CharacterSelectScreenPatches.reskinLock.move(Settings.WIDTH / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale);
            CharacterSelectScreenPatches.CF_RIGHT_ARROW = ImageMaster.CF_RIGHT_ARROW;
            CharacterSelectScreenPatches.CF_LEFT_ARROW = ImageMaster.CF_LEFT_ARROW;
        }

        @SpirePatch(clz = CharacterOption.class, method = "renderInfo")
        public static class CharacterOptionRenderInfoPatch {
            @SpireInsertPatch(locator = CharacterSelectScreenPatches.CharacterSelectScreenPatch_Initialize.renderRelicsLocator.class, localvars = {"infoX", "charInfo", "flavorText"})
            public static SpireReturn<Void> Insert(CharacterOption _instance, SpriteBatch sb, float infoX, CharSelectInfo charInfo, @ByRef String[] flavorText) {
                CharacterSelectScreenPatches.allTextInfoX = infoX - 200.0F * Settings.scale;
                for (AbstractSkinCharacter character : CharacterSelectScreenPatches.characters) {
                    if (charInfo.name.equals(character.id))
                        flavorText[0] = (character.skins[character.reskinCount]).DESCRIPTION;
                }
                return SpireReturn.Continue();
            }
        }

        private static class renderRelicsLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(CharacterOption.class, "renderRelics");
                int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, (Matcher) methodCallMatcher);
                return lines;
            }
        }

        @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
        public static class CharacterSelectScreenPatch_Render {
            @SpirePostfixPatch
            public static void Initialize(CharacterSelectScreen __instance, SpriteBatch sb) {
                for (CharacterOption o : __instance.options) {
                    for (AbstractSkinCharacter c : CharacterSelectScreenPatches.characters) {
                        c.InitializeReskinCount();
                        if (o.name.equals(c.id) && o.selected) {
                            CharacterSelectScreenPatches.reskinRight.render(sb);
                            CharacterSelectScreenPatches.reskinLeft.render(sb);
                            c.skins[c.reskinCount].extraHitboxRender(sb);
                            if (CharacterSelectScreenPatches.reskinRight.hovered || Settings.isControllerMode) {
                                sb.setColor(Color.WHITE.cpy());
                            } else {
                                sb.setColor(Color.LIGHT_GRAY.cpy());
                            }
                            sb.draw(CharacterSelectScreenPatches.CF_RIGHT_ARROW, Settings.WIDTH / 2.0F + CharacterSelectScreenPatches
                                    .reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX - 29.0F, CharacterSelectScreenPatches
                                    .buttonY * Settings.scale - 56.0F, 69.0F, 51.0F, 138.0F, 102.0F, Settings.scale * CharacterSelectScreenPatches

                                    .buttonScale, Settings.scale * CharacterSelectScreenPatches.buttonScale, 0.0F, 0, 0, 138, 102, false, false);
                            if (CharacterSelectScreenPatches.reskinLeft.hovered || Settings.isControllerMode) {
                                sb.setColor(Color.WHITE.cpy());
                            } else {
                                sb.setColor(Color.LIGHT_GRAY.cpy());
                            }
                            sb.draw(CharacterSelectScreenPatches.CF_LEFT_ARROW, Settings.WIDTH / 2.0F - CharacterSelectScreenPatches
                                    .reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX - 64.0F, CharacterSelectScreenPatches
                                    .buttonY * Settings.scale - 56.0F, 69.0F, 51.0F, 138.0F, 102.0F, Settings.scale * CharacterSelectScreenPatches

                                    .buttonScale, Settings.scale * CharacterSelectScreenPatches.buttonScale, 0.0F, 0, 0, 138, 102, false, false);

                            FontHelper.cardTitleFont.getData().setScale(1.0F);
                            FontHelper.losePowerFont.getData().setScale(0.8F);
                            FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, (c.skins[c.reskinCount]).NAME, Settings.WIDTH / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale, Settings.GOLD_COLOR.cpy());
                        }
                    }
                }
            }
        }

        @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
        public static class CharacterSelectScreenPatch_portraitSkeleton {
            @SpireInsertPatch(rloc = 62)
            public static void Insert(CharacterSelectScreen __instance, SpriteBatch sb) {
                for (CharacterOption o : __instance.options) {
                    for (AbstractSkinCharacter c : CharacterSelectScreenPatches.characters) {
                        c.InitializeReskinCount();
                        if (o.name.equals(c.id) && o.selected && c.skins[c.reskinCount].hasAnimation().booleanValue()) {
                            c.skins[c.reskinCount].render(sb);
                            if (CharacterSelectScreenPatches.char_effectsQueue.size() > 0) {
                                for (int k = 0; k < CharacterSelectScreenPatches.char_effectsQueue.size(); k++) {
                                    if (!(CharacterSelectScreenPatches.char_effectsQueue.get(k)).isDone) {
                                        (CharacterSelectScreenPatches.char_effectsQueue.get(k)).update();
                                        (CharacterSelectScreenPatches.char_effectsQueue.get(k)).render(sb);
                                    } else {
                                        if (CharacterSelectScreenPatches.char_effectsQueue_toRemove == null)
                                            CharacterSelectScreenPatches.char_effectsQueue_toRemove = new ArrayList<>();
                                        if (!CharacterSelectScreenPatches.char_effectsQueue_toRemove.contains(CharacterSelectScreenPatches.char_effectsQueue.get(k)))
                                            CharacterSelectScreenPatches.char_effectsQueue_toRemove.add(CharacterSelectScreenPatches.char_effectsQueue.get(k));
                                    }
                                }
                                if (CharacterSelectScreenPatches.char_effectsQueue_toRemove != null)
                                    CharacterSelectScreenPatches.char_effectsQueue.removeAll(CharacterSelectScreenPatches.char_effectsQueue_toRemove);
                            }
                        }
                    }
                }
            }
        }
    }

    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class CharacterOptionPatch_reloadAnimation {
        @SpireInsertPatch(rloc = 56)
        public static void Insert(CharacterOption __instance) {
            CharacterSelectScreenPatches.char_effectsQueue.clear();
            CharacterSelectScreenPatches.bgIMGUpdate = false;
            for (AbstractSkinCharacter c : CharacterSelectScreenPatches.characters) {
                c.InitializeReskinCount();
                if (__instance.name.equals(c.id)) {
                    c.skins[c.reskinCount].clearWhenClick();
                    if (c.skins[c.reskinCount].hasAnimation().booleanValue())
                        c.skins[c.reskinCount].loadPortraitAnimation();
                }
            }
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class CharacterSelectScreenPatch_Update {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance) {
            for (CharacterOption o : __instance.options) {
                for (AbstractSkinCharacter c : CharacterSelectScreenPatches.characters) {
                    c.InitializeReskinCount();
                    if (o.name.equals(c.id) && o.selected) {

                        if (!CharacterSelectScreenPatches.bgIMGUpdate) {
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                            CharacterSelectScreenPatches.bgIMGUpdate = true;
                        }
                        if (InputHelper.justClickedLeft && CharacterSelectScreenPatches.reskinLeft.hovered) {
                            CharacterSelectScreenPatches.reskinLeft.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (InputHelper.justClickedLeft && CharacterSelectScreenPatches.reskinRight.hovered) {
                            CharacterSelectScreenPatches.reskinRight.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (CharacterSelectScreenPatches.reskinLeft.justHovered || CharacterSelectScreenPatches.reskinRight.justHovered)
                            CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
                        CharacterSelectScreenPatches.reskinRight.move(Settings.WIDTH / 2.0F + CharacterSelectScreenPatches.reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale);
                        CharacterSelectScreenPatches.reskinLeft.move(Settings.WIDTH / 2.0F - CharacterSelectScreenPatches.reskin_W / 2.0F - CharacterSelectScreenPatches.reskinX_center + CharacterSelectScreenPatches.allTextInfoX, CharacterSelectScreenPatches.buttonY * Settings.scale);
                        CharacterSelectScreenPatches.reskinLeft.update();
                        CharacterSelectScreenPatches.reskinRight.update();
                        if (CharacterSelectScreenPatches.reskinRight.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            CharacterSelectScreenPatches.reskinRight.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            CharacterSelectScreenPatches.char_effectsQueue.clear();
                            if (c.reskinCount < c.skins.length - 1) {
                                c.reskinCount++;
                            } else {
                                c.reskinCount = 0;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            c.skins[c.reskinCount].playSelect();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "charInfo", c.skins[c.reskinCount]
                                    .updateCharInfo(
                                            (CharSelectInfo) ReflectionHacks.getPrivate(o, CharacterOption.class, "charInfo")));
                        }
                        if (CharacterSelectScreenPatches.reskinLeft.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            CharacterSelectScreenPatches.reskinLeft.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            CharacterSelectScreenPatches.char_effectsQueue.clear();
                            if (c.reskinCount > 0) {
                                c.reskinCount--;
                            } else {
                                c.reskinCount = c.skins.length - 1;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            c.skins[c.reskinCount].playSelect();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                            ReflectionHacks.setPrivate(o, CharacterOption.class, "charInfo", c.skins[c.reskinCount]
                                    .updateCharInfo(
                                            (CharSelectInfo) ReflectionHacks.getPrivate(o, CharacterOption.class, "charInfo")));
                        }
                        c.skins[c.reskinCount].update();
                        if (c.skins[c.reskinCount].extraHitboxClickCheck().booleanValue())
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        c.InitializeReskinCount();
                    }
                }
            }
        }

        @SpirePatch(clz = CharacterOption.class, method = "renderOptionButton")
        public static class CharacterOptionGlowPatch {
            @SpireInsertPatch(rloc = 6)
            public static SpireReturn<Void> Insert(CharacterOption __instance, SpriteBatch sb) {
                Color glowColor = (Color) ReflectionHacks.getPrivate(__instance, CharacterOption.class, "glowColor");
                if (__instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft")).NAMES[0]
                ) || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Elf")).NAMES[0])
                        || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Necromancer")).NAMES[0])
                        || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal")).NAMES[0])
                        || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Vampire")).NAMES[0])
                        || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Bishop")).NAMES[0])
                        || __instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Nemesis")).NAMES[0])
                        ||__instance.c.name.equals((CardCrawlGame.languagePack.getCharacterString("shadowverse:Dragon")).NAMES[0])
                );
                if (__instance.selected) {
                    glowColor.r = 0.0F;
                    glowColor.g = 1.0F;
                    glowColor.b = 1.0F;
                    sb.setColor(glowColor);
                } else {
                    sb.setColor(CharacterSelectScreenPatches.BLACK_OUTLINE_COLOR);
                }
                return SpireReturn.Continue();
            }
        }
    }
}
