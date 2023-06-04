 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.cards.Neutral.Temp.Muse;


 public class MusePrincessPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:MusePrincessPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MusePrincessPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public MusePrincessPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/MusePrincessPower.png");
   }

   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {
     boolean hasMuse = false;
     for (AbstractCard c: AbstractDungeon.player.hand.group){
       if (c instanceof Muse){
         hasMuse=true;
         break;
       }
     }
     if (card instanceof NaterranGreatTree && !hasMuse){
       addToBot((AbstractGameAction)new SFXAction("MusePrincessPower"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Muse()));
     }
   }

   @Override
   public void atStartOfTurn() {
     addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner,this.owner,this));
   }
 }


