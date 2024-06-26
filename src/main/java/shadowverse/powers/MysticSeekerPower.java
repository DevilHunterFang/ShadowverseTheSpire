 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.action.SpellBoostAction;
 
 public class MysticSeekerPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:MysticSeeker";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MysticSeeker");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int counter = 0;
   private AbstractCard card;
   
   public MysticSeekerPower(AbstractCreature owner, AbstractCard card) {
     this.name = NAME;
     this.ID = "shadowverse:MysticSeeker";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     this.card = card;
     updateDescription();
     this.img = new Texture("img/powers/MysticSeekerPower.png");
   }
 
   
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     this.counter++;
   }
 
   
   public void atStartOfTurnPostDraw() {
     flash();
     if (this.counter <= 3) {
       addToBot((AbstractGameAction)new SpellBoostAction(AbstractDungeon.player, this.card, AbstractDungeon.player.hand.group));
     } else {
       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 3));
     } 
     this.counter = 0;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }

