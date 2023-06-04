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
 import shadowverse.cards.Witch.Basic.Insight;
 import shadowverse.cards.Witch.WitchPool;
 import shadowverse.effect.ShadowverseEnergyOrb;
 import shadowverse.patch.CharacterSelectScreenPatches;

 import java.util.ArrayList;


 public class Witchcraft
   extends AbstractShadowversePlayer
 {


     public static class Enums
   {
     @SpireEnum
     public static PlayerClass WITCHCRAFT;
     @SpireEnum(name = "WITCH_BLUE_COLOR")
     public static AbstractCard.CardColor COLOR_BLUE;
     @SpireEnum(name = "WITCH_BLUE_COLOR")
     public static CardLibrary.LibraryType TYPE_BLUE;


   }
   public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");

   public static final String WITCHCRAFT_SHOULDER_2 = "img/character/Witchcraft/shoulder.png";
   public static final String WITCHCRAFT_SHOULDER_1 = "img/character/Witchcraft/shoulder.png";
   public static final String WITCHCRAFT_CORPSE = "img/character/Witchcraft/corpse.png";
   public static shadowverse.animation.AbstractAnimation bigAnimation = new shadowverse.animation.AbstractAnimation("img/animation/Witchcraft/class_1803.atlas", "img/animation/Witchcraft/class_1803.json", com.megacrit.cardcrawl.core.Settings.M_W / 1600.0F, com.megacrit.cardcrawl.core.Settings.M_W / 2.0F, com.megacrit.cardcrawl.core.Settings.M_H / 2.0F, 0F, 0F);
   private static Texture BASE_LAYER = new Texture("img/ui/layer_witch.png");

   public Witchcraft(String name) {
     super(name, Enums.WITCHCRAFT, new ShadowverseEnergyOrb(null, null,null,BASE_LAYER), (AbstractAnimation)new SpriterAnimation(((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).scmlURL));
     initializeClass(null, ((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).SHOULDER1, ((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).SHOULDER2, ((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
     bigAnimation.setVisible(false);
     this.cardPool= new WitchPool(0);
   }
 
   
   public ArrayList<String> getStartingDeck() {
     ArrayList<String> starterDeck = new ArrayList<>(); int i;
     for (i = 0; i < 4; i++) {
       starterDeck.add("shadowverse:Strike_W");
     }
     for (i = 0; i < 4; i++) {
       starterDeck.add("shadowverse:Defend_W");
     }
     starterDeck.add("shadowverse:Insight");
     starterDeck.add("shadowverse:FieryEmbrace");
     return starterDeck;
   }
 
   
   public ArrayList<String> getStartingRelics() {
     ArrayList<String> retVal = new ArrayList<>();
     retVal.add("shadowverse:Offensive");
     UnlockTracker.markRelicAsSeen("shadowverse:Offensive");
     return retVal;
   }
 
   
   public CharSelectInfo getLoadout() {
     return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 70, 70, 0, 99, 5, (AbstractPlayer)this,
         getStartingRelics(), 
         getStartingDeck(), false);
   }
 
 
   
   public String getTitle(PlayerClass playerClass) {
     return charStrings.NAMES[0];
   }
 
   
   public AbstractCard.CardColor getCardColor() {
     return Enums.COLOR_BLUE;
   }
 
   
   public Color getCardRenderColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public AbstractCard getStartCardForEvent() {
     return (AbstractCard)new Insight();
   }
 
   
   public Color getCardTrailColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public int getAscensionMaxHPLoss() {
     return 5;
   }
 
   
   public BitmapFont getEnergyNumFont() {
     return FontHelper.energyNumFontGreen;
   }
 
   
   public void doCharSelectScreenSelectEffect() {
     ((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).playSelect();
     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
   }
 
   
   public String getCustomModeCharacterButtonSoundKey() {
     return "AUTOMATON_ORB_SPAWN";
   }
 
   
   public String getLocalizedCharacterName() {
     return charStrings.NAMES[0];
   }
 
   
   public AbstractPlayer newInstance() {
     return (AbstractPlayer)new Witchcraft(this.name);
   }
 
   
   public String getSpireHeartText() {
     return SpireHeart.DESCRIPTIONS[8];
   }
 
   
   public Color getSlashAttackColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
   }
 
   
   public String getVampireText() {
     return Vampires.DESCRIPTIONS[0];
   }
 


     public void damage(DamageInfo info) {
       super.damage(info);
       ((CharacterSelectScreenPatches.characters[0]).skins[(CharacterSelectScreenPatches.characters[0]).reskinCount]).playHurtSound(lastDamageTaken);
     }
   public static shadowverse.animation.AbstractAnimation getBigAnimation() {
     return bigAnimation;
   }
 }

