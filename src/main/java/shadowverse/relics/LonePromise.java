 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.RepairMode;


 public class LonePromise
   extends CustomRelic
 {
   public static final String ID = "shadowverse:LonePromise";
   public static final String IMG = "img/relics/LonePromise.png";
   public static final String OUTLINE_IMG = "img/relics/outline/LonePromise_Outline.png";


   public LonePromise() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.SOLID);
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
       AbstractCard c = (AbstractCard)new RepairMode();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), 1));
     } 
     if (this.counter == 2) {
       flash();
       addToBot((AbstractGameAction)new DrawCardAction(1));
       this.counter = -1;
       this.grayscale = true;
     } 
   }
 
   
   public void onVictory() {
     this.counter = -1;
     this.grayscale = false;
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new LonePromise();
   }
 }
