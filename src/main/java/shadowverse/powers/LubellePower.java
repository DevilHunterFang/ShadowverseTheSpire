 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.cards.Neutral.Temp.NecroAnimals;


 public class LubellePower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:LubellePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:LubellePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public LubellePower(AbstractCreature owner,int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/LubellePower.png");
   }

   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
   }

   @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card instanceof NaterranGreatTree){
       addToBot((AbstractGameAction)new SFXAction("LubellePower"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NecroAnimals(),this.amount));
     }
   }

 }


