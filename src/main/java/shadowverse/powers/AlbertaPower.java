 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Elf.Long.Alberta;


 public class AlbertaPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:AlbertaPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AlbertaPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean upgraded;
   private boolean trigger;

   public AlbertaPower(AbstractCreature owner,boolean upgraded) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.upgraded = upgraded;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/AlbertaPower.png");
   }
   

   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + (this.upgraded?DESCRIPTIONS[2]:DESCRIPTIONS[1]) + DESCRIPTIONS[3];
   }
 
   @Override
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
           count++;
       }
       if (count == 3 && !trigger){
           AbstractCard c = new Alberta();
           if (upgraded)
               c.upgrade();
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
           addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
           trigger = true;
       }
   }

     @Override
     public void atStartOfTurn() {
         trigger = false;
     }

     public void playApplyPowerSfx() {
         CardCrawlGame.sound.play("AlbertaPower", 0.0F);
     }
 }

