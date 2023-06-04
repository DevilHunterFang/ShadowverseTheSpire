 package shadowverse.powers;
 


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


 public class InsatiableDesirePower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:InsatiableDesirePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:InsatiableDesirePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public InsatiableDesirePower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     loadRegion("carddraw");
   }

   public void atStartOfTurn() {
     flash();
     addToBot(new DrawCardAction(1));
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }

