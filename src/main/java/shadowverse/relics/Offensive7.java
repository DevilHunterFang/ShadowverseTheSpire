 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BurstPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.Shadowverse;
 import shadowverse.action.PlaceAmulet;


 public class Offensive7
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive7";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";
   private boolean trigger = false;

   public Offensive7() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onCardDraw(AbstractCard drawnCard) {
         if (drawnCard.type == AbstractCard.CardType.CURSE && Shadowverse.canSpawnAmuletOrb()){
             addToTop(new PlaceAmulet(drawnCard,AbstractDungeon.player.hand));
             if (!this.trigger){
                 addToTop(new DrawCardAction(1));
                 trigger = true;
             }
         }
     }

     @Override
     public void atBattleStart() {
         trigger = false;
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive7();
   }
 }

