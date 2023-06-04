 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.action.NecromanceAction;
 import shadowverse.cards.Neutral.Temp.Skullfather;
 import shadowverse.patch.CharacterSelectScreenPatches;
 import shadowverse.powers.Cemetery;


 public class ShinobuBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:ShinobuBOSS";
   public static final String IMG = "img/relics/ShinobuBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/LunaBOSS_Outline.png";

   public ShinobuBOSS() {
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
         if (playerNecromance>=4){
             flash();
             addToBot((AbstractGameAction)new NecromanceAction(4,null,
                     (AbstractGameAction)new MakeTempCardInHandAction(new Skullfather(),1)));
         }
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
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive3.ID)
                 &&(CharacterSelectScreenPatches.characters[2]).reskinCount == 2;
     }

 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new ShinobuBOSS();
   }
 }


