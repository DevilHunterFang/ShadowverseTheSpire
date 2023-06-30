 package shadowverse.cards.Vampire.Wrath;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Vampire;
import shadowverse.powers.DevilSheepPower;


 public class DevilSheep
   extends CustomCard
 {
   public static final String ID = "shadowverse:DevilSheep";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DevilSheep");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DevilSheep.png";

   public DevilSheep() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 5;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DevilSheepPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DevilSheep();
   }
 }

