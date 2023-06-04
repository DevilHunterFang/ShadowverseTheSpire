 package shadowverse.powers;


 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.ForestBat;


 public class OldBloodKingPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:OldBloodKingPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OldBloodKingPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public OldBloodKingPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/OldBloodKingPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999;
     updateExistingForestBat();
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   private void updateExistingForestBat() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c instanceof ForestBat) {
         if (!c.upgraded) {
           c.baseDamage = 4 + this.amount;
           continue;
         }
         c.baseDamage = 6 + this.amount;
       }
     }
     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
       if (c instanceof ForestBat) {
         if (!c.upgraded) {
           c.baseDamage = 4 + this.amount;
           continue;
         }
         c.baseDamage = 6 + this.amount;
       }
     }
     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
       if (c instanceof ForestBat) {
         if (!c.upgraded) {
           c.baseDamage = 4 + this.amount;
           continue;
         }
         c.baseDamage = 6 + this.amount;
       }
     }
     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
       if (c instanceof ForestBat) {
         if (!c.upgraded) {
           c.baseDamage = 4 + this.amount;
           continue;
         }
         c.baseDamage = 6 + this.amount;
       }
     }
   }

   public void onDrawOrDiscard() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c instanceof ForestBat) {
         if (!c.upgraded) {
           c.baseDamage = 4 + this.amount;
           continue;
         }
         c.baseDamage = 6 + this.amount;
       }
     }
   }
 }


