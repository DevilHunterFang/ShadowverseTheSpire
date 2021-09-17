 package shadowverse.cards.Uncommon;


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
import shadowverse.characters.Vampire;
import shadowverse.powers.LilimPower;


 public class Lilim
   extends CustomCard
 {
   public static final String ID = "shadowverse:Lilim";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lilim");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lilim.png";

   public Lilim() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.isEthereal = true;
     this.baseMagicNumber= 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isEthereal = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Lilim"));
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new LilimPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lilim();
   }
 }

