package shadowverse.helper;

import CardAugments.CardAugmentsMod;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import shadowverse.augement.*;
import shadowverse.characters.Royal;

public class AugmentHelper {
    public static final String ID="shadowverse";
    public static void register() {
        //Register mod
        CardAugmentsMod.registerMod(ID, CardCrawlGame.languagePack.getUIString(ID).TEXT[0]);

        CardAugmentsMod.registerOrbCharacter(Royal.Enums.Royal);

        //Can add modifiers manually
        CardAugmentsMod.registerAugment(new ArtifactMod(), ID);
        CardAugmentsMod.registerAugment(new BurialMod(), ID);
        CardAugmentsMod.registerAugment(new CoinMod(), ID);
        CardAugmentsMod.registerAugment(new CondemnedMod(), ID);
        CardAugmentsMod.registerAugment(new EarthMod(), ID);
        CardAugmentsMod.registerAugment(new LootMod(), ID);
        CardAugmentsMod.registerAugment(new MachinaMod(), ID);
        CardAugmentsMod.registerAugment(new MysteriaMod(), ID);
        CardAugmentsMod.registerAugment(new NaturaMod(), ID);
        CardAugmentsMod.registerAugment(new ReanimateMod(), ID);
        CardAugmentsMod.registerAugment(new SpellboostMod(), ID);
        CardAugmentsMod.registerAugment(new WindMod(), ID);
    }
}