 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.Horse;
import shadowverse.cards.Neutral.Temp.Jeep;
import shadowverse.cards.Neutral.Temp.Motorbike;

import java.util.ArrayList;


 public class GoldPinya
   extends CustomRelic
 {
   public static final String ID = "shadowverse:GoldPinya";
   public static final String IMG = "img/relics/GoldPinya.png";
   public static final String OUTLINE_IMG = "img/relics/outline/GoldPinya_Outline.png";
     public static AbstractCard returnTrulyRandomRideInCombat(Random rng) {
         ArrayList<AbstractCard> list = new ArrayList<>();
         Horse horse = new Horse();
         Motorbike bike = new Motorbike();
         Jeep jeep = new Jeep();
         list.add(horse);
         list.add(bike);
         list.add(jeep);
         return list.get(rng.random(list.size() - 1));
     }

   public GoldPinya() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   @Override
   public void atTurnStart() {
     flash();
       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
       AbstractCard c = returnTrulyRandomRideInCombat(AbstractDungeon.cardRandomRng).makeCopy();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new GoldPinya();
   }
 }

