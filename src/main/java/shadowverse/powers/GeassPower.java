 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Curse.Geass;


 public class GeassPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:GeassPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:GeassPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private static AbstractCard geass;

   public GeassPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:GeassPower";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/GeassPower.png");
     this.geass = (AbstractCard)new Geass();
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] ;
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(geass.makeSameInstanceOf(), 1, true, true));
     } 
   }
 }

