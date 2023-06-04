 package shadowverse.cards.Neutral.Temp;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.unique.AttackFromDeckToHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Witchcraft;
 
 
 
 
 
 public class IsabellesConjuration
   extends CustomCard
 {
   public static final String ID = "shadowverse:IsabellesConjuration";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IsabellesConjuration");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/IsabellesConjuration.png";
   
   public IsabellesConjuration() {
     super("shadowverse:IsabellesConjuration", NAME, "img/cards/IsabellesConjuration.png", -2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     onChoseThisOption();
   }
   
   public void onChoseThisOption() {
     addToBot((AbstractGameAction)new SFXAction("IsabellesConjuration"));
     addToBot((AbstractGameAction)new AttackFromDeckToHandAction(1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new IsabellesConjuration();
   }
 }

