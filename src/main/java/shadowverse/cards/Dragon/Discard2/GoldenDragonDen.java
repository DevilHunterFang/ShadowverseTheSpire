 package shadowverse.cards.Dragon.Discard2;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Dragon;
 import shadowverse.powers.GoldenDragonDenPower;

 public class GoldenDragonDen
   extends CustomCard {
   public static final String ID = "shadowverse:GoldenDragonDen";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GoldenDragonDen");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GoldenDragonDen.png";

   public GoldenDragonDen() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isInnate = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new GoldenDragonDenPower(abstractPlayer,1)));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GoldenDragonDen();
   }
 }

