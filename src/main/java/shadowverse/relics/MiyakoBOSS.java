 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.colorless.Apparition;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.action.NecromanceAction;
 import shadowverse.patch.CharacterSelectScreenPatches;
 import shadowverse.powers.Cemetery;


 public class MiyakoBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:MiyakoBOSS";
   public static final String IMG = "img/relics/MiyakoBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/LunaBOSS_Outline.png";


   public MiyakoBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
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
         if (playerNecromance>=6 && !this.grayscale){
             flash();
             this.counter--;
             addToBot((AbstractGameAction)new NecromanceAction(6,null,
                     (AbstractGameAction)new MakeTempCardInHandAction(new Apparition(),1)));
         }
         if (this.counter == 0) {
             flash();
             this.grayscale = true;
         }
     }

     @Override
     public void atBattleStart() {
         this.counter = 3;
         addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
     }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive3.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     @Override
     public void onVictory() {
         this.counter = -1;
         this.grayscale = false;
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive3.ID) &&
                 (CharacterSelectScreenPatches.characters[2]).reskinCount == 1;
     }

 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new MiyakoBOSS();
   }
 }


