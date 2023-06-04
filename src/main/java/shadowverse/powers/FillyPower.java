 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Elf.Long.SkyDevouring;
 import shadowverse.cards.Neutral.Temp.Fairy;


 public class FillyPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:FillyPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FillyPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
     private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

   public FillyPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/FillyPower.png");
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
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
       if (card instanceof Fairy){
           addToBot(new SFXAction("FillyPower"));
           addToBot(new GainEnergyAction(1));
       }
       int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
       if (count == 2){
           addToBot(new SFXAction("FillyPower2"));
           for (AbstractCard c : AbstractDungeon.player.hand.group){
               addToBot(new GainBlockAction(AbstractDungeon.player,3));
           }
       }
       if (count == 5){
           addToBot(new SFXAction("FillyPower2"));
           addToBot(new DrawCardAction(2));
       }
       if (count == 8){
           addToBot(new SFXAction("FillyPower2"));
           addToBot(new AbstractGameAction() {
               @Override
               public void update() {
                   AbstractCard tmp = new SkyDevouring();
                   tmp.setCostForTurn(0);
                   tmp.costForTurn = 0;
                   tmp.isCostModified = true;
                   tmp.exhaustOnUseOnce = true;
                   tmp.exhaust = true;
                   tmp.rawDescription += " NL " + TEXT + " 。";
                   tmp.initializeDescription();
                   tmp.applyPowers();
                   AbstractDungeon.player.hand.addToTop(tmp);
                   this.isDone = true;
               }
           });
       }
   }

   @Override
   public void atStartOfTurn() {
       addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player,this));
       updateDescription();
   }
 }

