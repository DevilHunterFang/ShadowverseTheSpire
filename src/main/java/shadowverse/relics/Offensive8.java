 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.Shadowverse;
 import shadowverse.action.PlaceAmulet;
 import shadowverse.powers.OverflowPower;


 public class Offensive8
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive8";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";

   public Offensive8() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void atBattleStart() {
         addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new OverflowPower(AbstractDungeon.player,1)));
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive8();
   }
 }

