//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package shadowverse.patch;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import shadowverse.characters.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpirePatch(
        clz = Cutscene.class,
        method = "<ctor>"
)
public class CutsceneChangePatches {
    public CutsceneChangePatches() {
    }

    @SpirePostfixPatch
    public static void patch(Cutscene __instance, PlayerClass chosenClass) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            Texture customBg = null;
            if (AbstractDungeon.player instanceof Royal){
                customBg = ImageMaster.loadImage("img/scene/royalBg.jpg");
            }else if (AbstractDungeon.player instanceof Witchcraft){
                customBg = ImageMaster.loadImage("img/scene/witchBg.png");
            }else if (AbstractDungeon.player instanceof Elf){
                customBg = ImageMaster.loadImage("img/scene/elfBg.png");
            }else if (AbstractDungeon.player instanceof Necromancer){
                customBg = ImageMaster.loadImage("img/scene/necroBg.png");
            }else if (AbstractDungeon.player instanceof Vampire){
                customBg = ImageMaster.loadImage("img/scene/vampBg.png");
            }else if (AbstractDungeon.player instanceof Nemesis){
                customBg = ImageMaster.loadImage("img/scene/nemesisBg.png");
            }else if (AbstractDungeon.player instanceof Bishop){
                customBg = ImageMaster.loadImage("img/scene/bishopBg.png");
            } else if (AbstractDungeon.player instanceof Dragon) {
                customBg = ImageMaster.loadImage("img/scene/dragonBg.png");
            }
            if (customBg != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("bgImg");
                    f.setAccessible(true);
                    Texture oldBg = (Texture) f.get(__instance);
                    oldBg.dispose();
                    f.set(__instance, customBg);
                } catch (NoSuchFieldException | IllegalAccessException var8) {
                    var8.printStackTrace();
                }
            }

            List<CutscenePanel> customPanels = new ArrayList();
            if (AbstractDungeon.player instanceof Royal){
                if ((CharacterSelectScreenPatches.characters[3]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/royal1.png"));
                    customPanels.add(new CutscenePanel("img/scene/royal2.png"));
                    customPanels.add(new CutscenePanel("img/scene/royal3.png"));
                }else if ((CharacterSelectScreenPatches.characters[3]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/shizuru1.png"));
                    customPanels.add(new CutscenePanel("img/scene/royal2.png"));
                    customPanels.add(new CutscenePanel("img/scene/shizuru3.png"));
                }
                else if ((CharacterSelectScreenPatches.characters[3]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/monika1.png"));
                    customPanels.add(new CutscenePanel("img/scene/royal2.png"));
                    customPanels.add(new CutscenePanel("img/scene/monika3.png"));
                }
            }else if (AbstractDungeon.player instanceof Witchcraft){
                if ((CharacterSelectScreenPatches.characters[0]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/witch1.png"));
                    customPanels.add(new CutscenePanel("img/scene/witch2.png"));
                    customPanels.add(new CutscenePanel("img/scene/witch3.png"));
                }else if ((CharacterSelectScreenPatches.characters[0]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/kyaru1.png"));
                    customPanels.add(new CutscenePanel("img/scene/witch2.png"));
                    customPanels.add(new CutscenePanel("img/scene/kyaru2.png"));
                }else if ((CharacterSelectScreenPatches.characters[0]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/anne1.png"));
                    customPanels.add(new CutscenePanel("img/scene/witch2.png"));
                    customPanels.add(new CutscenePanel("img/scene/anne3.png"));
                }else if ((CharacterSelectScreenPatches.characters[0]).reskinCount == 3){
                    customPanels.add(new CutscenePanel("img/scene/halloween_kyouka.png"));
                    customPanels.add(new CutscenePanel("img/scene/witch2.png"));
                    customPanels.add(new CutscenePanel("img/scene/halloween_kyouka3.png"));
                }
            }else if (AbstractDungeon.player instanceof Elf){
                if ((CharacterSelectScreenPatches.characters[1]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/elf1.png"));
                    customPanels.add(new CutscenePanel("img/scene/elf2.png"));
                    customPanels.add(new CutscenePanel("img/scene/elf3.png"));
                }else if ((CharacterSelectScreenPatches.characters[1]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/kokkoro1.png"));
                    customPanels.add(new CutscenePanel("img/scene/elf2.png"));
                    customPanels.add(new CutscenePanel("img/scene/kokkoro3.png"));
                }
            }else if (AbstractDungeon.player instanceof Necromancer){
                if ((CharacterSelectScreenPatches.characters[2]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/necro1.png"));
                    customPanels.add(new CutscenePanel("img/scene/necro2.png"));
                    customPanels.add(new CutscenePanel("img/scene/necro3.png"));
                }else if ((CharacterSelectScreenPatches.characters[2]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/miyako1.png"));
                    customPanels.add(new CutscenePanel("img/scene/necro2.png"));
                    customPanels.add(new CutscenePanel("img/scene/miyako3.png"));
                }else if ((CharacterSelectScreenPatches.characters[2]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/shinobu1.png"));
                    customPanels.add(new CutscenePanel("img/scene/shinobu2.png"));
                    customPanels.add(new CutscenePanel("img/scene/shinobu3.png"));
                }
            }else if (AbstractDungeon.player instanceof Vampire){
                if ((CharacterSelectScreenPatches.characters[4]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/vamp1.png"));
                    customPanels.add(new CutscenePanel("img/scene/vamp2.png"));
                    customPanels.add(new CutscenePanel("img/scene/vamp3.png"));
                }else if ((CharacterSelectScreenPatches.characters[4]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/io1.png"));
                    customPanels.add(new CutscenePanel("img/scene/io2.png"));
                    customPanels.add(new CutscenePanel("img/scene/io3.png"));
                }else if ((CharacterSelectScreenPatches.characters[4]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/vanpi1.png"));
                    customPanels.add(new CutscenePanel("img/scene/vanpi2.png"));
                    customPanels.add(new CutscenePanel("img/scene/vanpi3.png"));
                }
            }else if (AbstractDungeon.player instanceof Nemesis){
                if ((CharacterSelectScreenPatches.characters[6]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/nemesis1.png"));
                    customPanels.add(new CutscenePanel("img/scene/nemesis2.png"));
                    customPanels.add(new CutscenePanel("img/scene/nemesis3.png"));
                }else if ((CharacterSelectScreenPatches.characters[6]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/ralmia1.png"));
                    customPanels.add(new CutscenePanel("img/scene/nemesis2.png"));
                    customPanels.add(new CutscenePanel("img/scene/ralmia3.png"));
                }else if ((CharacterSelectScreenPatches.characters[6]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/ameth1.png"));
                    customPanels.add(new CutscenePanel("img/scene/nemesis2.png"));
                    customPanels.add(new CutscenePanel("img/scene/ameth3.png"));
                }else if ((CharacterSelectScreenPatches.characters[6]).reskinCount == 3){
                    customPanels.add(new CutscenePanel("img/scene/kaiser1.png"));
                    customPanels.add(new CutscenePanel("img/scene/nemesis2.png"));
                    customPanels.add(new CutscenePanel("img/scene/kaiser3.png"));
                }
            }else if (AbstractDungeon.player instanceof Bishop){
                if ((CharacterSelectScreenPatches.characters[5]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/bishop1.png"));
                    customPanels.add(new CutscenePanel("img/scene/bishop2.png"));
                    customPanels.add(new CutscenePanel("img/scene/bishop3.png"));
                }else if ((CharacterSelectScreenPatches.characters[5]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/yukari1.png"));
                    customPanels.add(new CutscenePanel("img/scene/bishop2.png"));
                    customPanels.add(new CutscenePanel("img/scene/yukari3.png"));
                }else if ((CharacterSelectScreenPatches.characters[5]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/saren_summer.png"));
                    customPanels.add(new CutscenePanel("img/scene/saren_summer2.png"));
                    customPanels.add(new CutscenePanel("img/scene/saren_summer3.png"));
                }
            }
            else if (AbstractDungeon.player instanceof Dragon){
                if ((CharacterSelectScreenPatches.characters[7]).reskinCount == 0){
                    customPanels.add(new CutscenePanel("img/scene/kaya1.png"));
                    customPanels.add(new CutscenePanel("img/scene/dragon2.png"));
                    customPanels.add(new CutscenePanel("img/scene/kaya3.png"));
                }else if ((CharacterSelectScreenPatches.characters[7]).reskinCount == 1){
                    customPanels.add(new CutscenePanel("img/scene/grea1.png"));
                    customPanels.add(new CutscenePanel("img/scene/dragon2.png"));
                    customPanels.add(new CutscenePanel("img/scene/grea3.png"));
                }else if ((CharacterSelectScreenPatches.characters[7]).reskinCount == 2){
                    customPanels.add(new CutscenePanel("img/scene/inori1.png"));
                    customPanels.add(new CutscenePanel("img/scene/dragon2.png"));
                    customPanels.add(new CutscenePanel("img/scene/inori3.png"));
                }
            }
            if (customPanels != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("panels");
                    f.setAccessible(true);
                    ArrayList<CutscenePanel> panels = (ArrayList) f.get(__instance);
                    Iterator var6 = panels.iterator();

                    while (var6.hasNext()) {
                        CutscenePanel panel = (CutscenePanel) var6.next();
                        panel.dispose();
                    }

                    panels.clear();
                    panels.addAll(customPanels);
                } catch (NoSuchFieldException | IllegalAccessException var9) {
                    var9.printStackTrace();
                }
            }
        }

    }
}
