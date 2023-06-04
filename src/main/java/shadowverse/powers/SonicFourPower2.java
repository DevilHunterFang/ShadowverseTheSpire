 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.SonicFour;


 public class SonicFourPower2
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:SonicFourPower2";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:SonicFourPower2");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public SonicFourPower2(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/SonicFourPower.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }


   public void atStartOfTurnPostDraw() {
     SonicFour sonicFour = new SonicFour();
     boolean flag = true;
     for (AbstractCard c: AbstractDungeon.player.hand.group){
       if (c instanceof SonicFour)
         flag = false;
     }
     if (flag){
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)sonicFour, 1));
     }
   }
 }
