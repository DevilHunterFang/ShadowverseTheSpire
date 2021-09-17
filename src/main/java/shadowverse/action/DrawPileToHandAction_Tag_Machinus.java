 package shadowverse.action;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 
 public class DrawPileToHandAction_Tag_Machinus
   extends AbstractGameAction {
   private AbstractPlayer p;
   private AbstractCard.CardTags tagToCheck;
   private AbstractCard.CardTags tagToCheck2;
   
   public DrawPileToHandAction_Tag_Machinus(int amount, AbstractCard.CardTags tag, AbstractCard.CardTags tag2) {
     this.p = AbstractDungeon.player;
     setValues((AbstractCreature)this.p, (AbstractCreature)this.p, amount);
     this.actionType = ActionType.CARD_MANIPULATION;
     this.duration = Settings.ACTION_DUR_MED;
     this.tagToCheck = tag;
     this.tagToCheck2 = tag2;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_MED) {
       if (this.p.drawPile.isEmpty()) {
         this.isDone = true;
         return;
       } 
       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
       for (AbstractCard c : this.p.drawPile.group) {
         if (c.hasTag(this.tagToCheck) || c.hasTag(this.tagToCheck2))
           tmp.addToRandomSpot(c); 
       } 
       if (tmp.size() == 0) {
         this.isDone = true;
         return;
       } 
       for (int i = 0; i < this.amount; i++) {
         if (!tmp.isEmpty()) {
           tmp.shuffle();
           AbstractCard card = tmp.getBottomCard();
           if (card.cost > 0){
             card.cost -= 1;
             card.costForTurn -= 1;
             card.isCostModified = true;
           }
           tmp.removeCard(card);
           if (this.p.hand.size() == 10) {
             this.p.drawPile.moveToDiscardPile(card);
             this.p.createHandIsFullDialog();
           } else {
             card.unhover();
             card.lighten(true);
             card.setAngle(0.0F);
             card.drawScale = 0.12F;
             card.targetDrawScale = 0.75F;
             card.current_x = CardGroup.DRAW_PILE_X;
             card.current_y = CardGroup.DRAW_PILE_Y;
             this.p.drawPile.removeCard(card);
             AbstractDungeon.player.hand.addToTop(card);
             AbstractDungeon.player.hand.refreshHandLayout();
             AbstractDungeon.player.hand.applyPowers();
           } 
         } 
       } 
       this.isDone = true;
     } 
     tickDuration();
   }
 }

