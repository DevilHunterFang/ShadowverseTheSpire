 package shadowverse.cards.Necromancer.Default;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.Necromancer;


 public class SowDeathReapLife extends CustomCard {
   public static final String ID = "shadowverse:SowDeathReapLife";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SowDeathReapLife");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SowDeathReapLife.png";

   public SowDeathReapLife() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
       this.baseMagicNumber = 0;
       this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }

     @Override
     public void atTurnStart(){
             this.baseMagicNumber++;
             this.magicNumber = this.baseMagicNumber;
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
           addToBot((AbstractGameAction)new ReanimateAction(this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SowDeathReapLife();
   }
 }

