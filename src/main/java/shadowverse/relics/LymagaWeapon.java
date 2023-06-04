 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.cards.Elf.Wood.GreenWoodGuardian;


 public class LymagaWeapon
   extends CustomRelic
 {
   public static final String ID = "shadowverse:LymagaWeapon";
   public static final String IMG = "img/relics/LymagaWeapon.png";
   public static final String OUTLINE_IMG = "img/relics/outline/LymagaWeapon_Outline.png";

   public LymagaWeapon() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
   }

     public void atTurnStart() {
         AbstractCard c = (AbstractCard)new GreenWoodGuardian();
         c.setCostForTurn(0);
         c.isCostModifiedForTurn = true;
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
     }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new LymagaWeapon();
   }
 }

