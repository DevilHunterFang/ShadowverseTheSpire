 package shadowverse.cards.Elf.Wood;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.Horse;
 import shadowverse.characters.Elf;
import shadowverse.powers.VarmintHunterPower;


 public class VarmintHunter extends CustomCard {
   public static final String ID = "shadowverse:VarmintHunter";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VarmintHunter");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/VarmintHunter.png";

   public VarmintHunter() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 6;
     this.cardsToPreview = (AbstractCard)new Horse();
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       addToBot((AbstractGameAction)new SFXAction("VarmintHunter"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VarmintHunterPower((AbstractCreature)abstractPlayer,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new VarmintHunter();
   }
 }

