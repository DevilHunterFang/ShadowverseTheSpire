 package shadowverse.powers;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.ManaForce;


 public class chushouHealPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:chushouHealPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:chushouHealPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public chushouHealPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
       loadRegion("regen");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   @Override
   public void onDeath() {
       AbstractCard c = (AbstractCard)new ManaForce();
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters){
           if (m.type == AbstractMonster.EnemyType.BOSS){
               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)m, (AbstractCreature)this.owner, this.amount));
           }
       }
   }

 }


