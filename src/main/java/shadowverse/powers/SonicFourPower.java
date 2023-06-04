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
import shadowverse.characters.AbstractShadowversePlayer;


 public class SonicFourPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:SonicFourPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:SonicFourPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int sonicFourCount;
   
   public SonicFourPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:SonicFourPower";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/SonicFourPower.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisTurn){
       if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE))
         this.sonicFourCount++;
     }
   }

   public void atStartOfTurnPostDraw() {
     SonicFour sonicFour = new SonicFour();
     if (this.sonicFourCount >= 4) {
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)sonicFour, 1));
     }
     this.sonicFourCount = 0;
   }
 }
