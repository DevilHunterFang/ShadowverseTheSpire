 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.MagicalPawn;
import shadowverse.cards.Witch.Chess.MagicalStrategy;


 public class MysticQueenPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:MysticQueenPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MysticQueenPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean attackPlayed;
   private boolean skillPlayed;
   private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

   public MysticQueenPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/MysticQueenPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
   }

   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999) {
       this.amount = 999;
     }
   }

   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     if (!skillPlayed){
       if (card.type == AbstractCard.CardType.SKILL){
         addToBot(new SFXAction("MysticQueenPower"));
         for(int i = 0; i < this.amount; i++) {
           addToBot(new MakeTempCardInHandAction(new MagicalPawn()));
         }
         skillPlayed = true;
       }
     }
     if (!attackPlayed){
       if (card.type == AbstractCard.CardType.ATTACK){
         addToBot(new SFXAction("MysticQueenPower"));
         for(int i = 0; i < this.amount; i++){
           AbstractCard tmp = new MagicalStrategy();
           tmp.exhaustOnUseOnce = true;
           tmp.exhaust = true;
           tmp.rawDescription += " NL "+TEXT+" 。";
           tmp.initializeDescription();
           tmp.applyPowers();
           AbstractDungeon.player.hand.addToTop(tmp);
         }
         attackPlayed = true;
       }
     }
   }

   @Override
   public void atStartOfTurn() {
     attackPlayed = false;
     skillPlayed = false;
   }
 }


