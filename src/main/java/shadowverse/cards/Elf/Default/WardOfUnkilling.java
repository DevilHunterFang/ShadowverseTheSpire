 package shadowverse.cards.Elf.Default;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Elf;
import shadowverse.powers.IzudiaPower;


 public class WardOfUnkilling extends CustomCard {
   public static final String ID = "shadowverse:WardOfUnkilling";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardOfUnkilling");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WardOfUnkilling.png";

   public WardOfUnkilling() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("WardOfUnkilling"));
       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, this.magicNumber));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new IzudiaPower((AbstractCreature)abstractPlayer, 1), 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WardOfUnkilling();
   }
 }

