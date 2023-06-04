 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.Horse;
import shadowverse.cards.Neutral.Temp.Jeep;
import shadowverse.cards.Neutral.Temp.Motorbike;

import java.util.ArrayList;
import java.util.Collections;


 public class LoxisPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:LoxisPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:LoxisPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private static ArrayList<AbstractCard> getRideCard(){
     ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
     list.add((AbstractCard)new Horse());
     list.add((AbstractCard)new Motorbike());
     list.add((AbstractCard)new Jeep());
     return list;
   }
   private ArrayList<AbstractCard> usedRideCard = getRideCard();

   public LoxisPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/LoxisPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     if (card.type == AbstractCard.CardType.POWER){
       if (usedRideCard.size()!=0){
         addToBot((AbstractGameAction)new SFXAction("LoxisPower1"));
         addToBot((AbstractGameAction)new GainEnergyAction(1));
         Collections.shuffle(usedRideCard);
         addToBot((AbstractGameAction)new SFXAction("LoxisPower2"));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(usedRideCard.get(0),1));
         usedRideCard.remove(0);
       }
     }
   }

   @Override
   public void atStartOfTurn(){
     usedRideCard = getRideCard();
   }
 }


