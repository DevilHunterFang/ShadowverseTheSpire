 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 
 
 public class NaterranTree
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:NaterranTree";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NaterranTree");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public NaterranTree(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:NaterranTree";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/NaterranTree.png");
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }
