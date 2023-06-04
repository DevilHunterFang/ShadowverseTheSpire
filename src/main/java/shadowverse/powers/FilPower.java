 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.Fil;

 public class FilPower extends AbstractPower {
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FilPower");
   public static final String POWER_ID = "shadowverse:FilPower";
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean doubleGain = false;

   public FilPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/FilPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   @Override
   public int onAttacked(DamageInfo info, int damageAmount) {
       if (damageAmount>=8)
           doubleGain=true;
       return damageAmount;
   }
   
   public void atStartOfTurn() {
       flash();
     if (this.doubleGain) {
         addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Fil(),2));
         doubleGain = false;
     } else {
         addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Fil(),1));
     }
   }
 }


