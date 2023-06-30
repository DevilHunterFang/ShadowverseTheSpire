 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Neutral.Temp.Zombie;
import shadowverse.powers.Cemetery;


 public class Offensive3
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive3";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";

   public Offensive3() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void atTurnStart() {
       int playerNecromance = 0;
       if (AbstractDungeon.player.hasPower(Cemetery.POWER_ID)){
           for (AbstractPower p :AbstractDungeon.player.powers){
               if (p.ID.equals(Cemetery.POWER_ID))
                   playerNecromance = p.amount;
           }
       }
       if (playerNecromance>=3){
           flash();
           addToBot(new NecromanceAction(3,null,
                   new MakeTempCardInHandAction(new Zombie(),1)));
       }
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive3();
   }
 }


