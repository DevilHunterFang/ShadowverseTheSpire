 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Status.EvolutionPoint;
 import shadowverse.characters.Bishop;


 public class AbdielPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:AbdielPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AbdielPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean hasTrigger = false;

   public AbdielPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/AbdielPower.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   @Override
   public void atStartOfTurn() {
     hasTrigger = false;
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.color == Bishop.Enums.COLOR_WHITE && card.type!= AbstractCard.CardType.SKILL && !hasTrigger) {
       hasTrigger = true;
       flash();
       AbstractCard ep = new EvolutionPoint();
       ep.upgrade();
       addToBot((AbstractGameAction)new GainEnergyAction(1));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(ep));
     } 
   }
 }

