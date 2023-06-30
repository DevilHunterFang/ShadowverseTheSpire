 package shadowverse.cards.Dragon.Ramp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Temp.WindblastDragon;
 import shadowverse.characters.Dragon;
 import shadowverse.powers.PolyphonicRoarPower;

 public class PolyphonicRoar
   extends CustomCard {
   public static final String ID = "shadowverse:PolyphonicRoar";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PolyphonicRoar");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PolyphonicRoar.png";

   public PolyphonicRoar() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.cardsToPreview = new WindblastDragon();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new PolyphonicRoarPower(abstractPlayer,1,this.upgraded)));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PolyphonicRoar();
   }
 }

