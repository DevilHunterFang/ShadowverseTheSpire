 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.cards.Neutral.Temp.GenerateNine;
import shadowverse.cards.Neutral.Temp.Manmaru1;


 public class AeneaPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:AeneaPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AeneaPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public AeneaPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/AeneaPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
   }

   public int onLoseHp(int damageAmount) {
       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
     return damageAmount;
   }

   public void atStartOfTurnPostDraw() {
     addToBot((AbstractGameAction)new SFXAction("AeneaPower"));
     if (this.owner.hasPower(BufferPower.POWER_ID)){
       AbstractCard c = (AbstractCard)new GenerateNine();
       if (EnergyPanel.getCurrentEnergy()>=4)
         c.setCostForTurn(4);
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new GenerateNine(),this.amount));
     } else
       addToTop((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Manmaru1(),this.amount));
   }

 }


