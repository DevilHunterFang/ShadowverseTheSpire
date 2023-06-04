 package shadowverse.cards.Elf.Default;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.characters.Elf;


 public class ZealotOfUnkilling
   extends CustomCard {
   public static final String ID = "shadowverse:ZealotOfUnkilling";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ZealotOfUnkilling");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ZealotOfUnkilling.png";

   public ZealotOfUnkilling() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot((AbstractGameAction)new SFXAction("ZealotOfUnkilling"));
       if (m != null && m.getIntentBaseDmg() >= 0) {
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)m, -this.magicNumber), -this.magicNumber));
       }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ZealotOfUnkilling();
   }
 }

