 package shadowverse.action;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

 import java.util.ArrayList;
 import java.util.Arrays;

 public class Choose2DifferentAction
   extends DiscoveryAction
 {
   private ArrayList<AbstractCard> card = new ArrayList<>();
   private boolean retrieveCard = false;
   private boolean secondTime;

   public Choose2DifferentAction(boolean secondTime, AbstractCard... card) {
     this.actionType = ActionType.CARD_MANIPULATION;
     this.duration = Settings.ACTION_DUR_FAST;
     this.amount = 1;
     this.secondTime = secondTime;
     this.card.addAll(Arrays.asList(card));
   }

     public Choose2DifferentAction(boolean secondTime, int amount, AbstractCard... card) {
         this.actionType = ActionType.CARD_MANIPULATION;
         this.duration = Settings.ACTION_DUR_FAST;
         this.amount = amount;
         this.secondTime = secondTime;
         this.card.addAll(Arrays.asList(card));
     }
 
   
   public void update() {
     ArrayList<AbstractCard> generatedCards = null;
     AbstractCard[] secondGroup = null;
     if (this.card.size() != 0) {
       generatedCards = this.card;
     }
     if (this.duration == Settings.ACTION_DUR_FAST) {
       AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], false);
       tickDuration();
       return;
     } 
     if (!this.retrieveCard) {
       if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
         AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
         if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
           disCard.upgrade();
         }
         addToBot(new MakeTempCardInHandAction(disCard,this.amount));
         generatedCards.remove(AbstractDungeon.cardRewardScreen.discoveryCard);
         secondGroup = new AbstractCard[generatedCards.size()];
         this.card.toArray(secondGroup);
         AbstractDungeon.cardRewardScreen.discoveryCard = null;
       } 
       this.retrieveCard = true;
     } 
     tickDuration();
     if (secondTime){
       addToBot(new Choose2DifferentAction(false,this.amount,secondGroup));
       this.secondTime = false;
     }
   }
 }


