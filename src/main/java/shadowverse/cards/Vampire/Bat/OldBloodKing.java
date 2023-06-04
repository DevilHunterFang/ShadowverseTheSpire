 package shadowverse.cards.Vampire.Bat;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.ForestBat;
 import shadowverse.characters.Vampire;
import shadowverse.powers.OldBloodKingPower;


 public class OldBloodKing
   extends CustomCard
 {
   public static final String ID = "shadowverse:OldBloodKing";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OldBloodKing");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OldBloodKing.png";

   public OldBloodKing() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber= 8;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new ForestBat();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new OldBloodKingPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OldBloodKing();
   }
 }

