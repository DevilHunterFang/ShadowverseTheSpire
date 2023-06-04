 package shadowverse.powers;


 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.Whisp;


 public class GenshinPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:GenshinPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:GenshinPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean triggerCheck = false;


   public GenshinPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/GenshinPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   @Override
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     if (!triggerCheck){
       AbstractDungeon.actionManager.cardsPlayedThisTurn.add(card);
       AbstractDungeon.actionManager.cardsPlayedThisTurn.add(card);
       addToBot((AbstractGameAction)new GainEnergyAction(1));
       triggerCheck = true;
       if (card instanceof Whisp){
           addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,card.block));
       }
     }
   }

   @Override
   public void atStartOfTurn(){
     triggerCheck = false;
   }
 }


