 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.FlameNGlass;


 public class FlameNGlassPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:FlameNGlassPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FlameNGlassPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public FlameNGlassPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/FlameNGlassPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
     if (damageAmount > 0)
       addToTop((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
     return damageAmount;
   }

   @Override
   public void atStartOfTurn() {
     AbstractCard c = (AbstractCard) new FlameNGlass();
     addToBot((AbstractGameAction)new SFXAction("FlameNGlassPower"));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), this.amount));
     addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:FlameNGlassPower"));
   }
 }


