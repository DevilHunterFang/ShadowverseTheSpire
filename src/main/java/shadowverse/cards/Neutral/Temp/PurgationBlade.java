 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.characters.Nemesis;


 public class PurgationBlade
   extends CustomCard {
   public static final String ID = "shadowverse:PurgationBlade";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PurgationBlade");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PurgationBlade.png";

   public PurgationBlade() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(2);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("PurgationBlade"));
     int str = 0;
     for (AbstractCard c:p.exhaustPile.group){
       if (c.type ==CardType.ATTACK){
         str++;
       }
     }
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new StrengthPower(p,str),str));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PurgationBlade();
   }
 }

