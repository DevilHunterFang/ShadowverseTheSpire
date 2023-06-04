 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.stance.Resonance;


 public class TranquilCog
   extends CustomRelic
 {
   public static final String ID = "shadowverse:TranquilCog";
   public static final String IMG = "img/relics/TranquilCog.png";
   public static final String OUTLINE_IMG = "img/relics/outline/TranquilCog_Outline.png";



   public TranquilCog() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SHOP, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     this.counter = 0;
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
   }
   
   public void atTurnStart() {
     if (!this.grayscale) {
       this.counter++;
       if (AbstractDungeon.player.stance.ID.equals(Resonance.STANCE_ID)){
         addToBot((AbstractGameAction)new DrawCardAction(1));
       }else {
         addToBot((AbstractGameAction)new DrawCardAction(2));
       }
     } 
     if (this.counter == 3) {
       flash();
       this.counter = -1;
       this.grayscale = true;
     } 
   }
 
   
   public void onVictory() {
     this.counter = -1;
     this.grayscale = false;
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new TranquilCog();
   }
 }
