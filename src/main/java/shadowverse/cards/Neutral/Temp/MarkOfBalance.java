 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Bishop;


 public class MarkOfBalance extends CustomCard {
   public static final String ID = "shadowverse:MarkOfBalance";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MarkOfBalance");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MarkOfBalance.png";
   private boolean triggered;

   public MarkOfBalance() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction) new SFXAction("MarkOfBalance"));
     addToBot((AbstractGameAction) new HealAction(abstractPlayer,abstractPlayer,1));
     addToBot((AbstractGameAction) new HealAction(abstractMonster,abstractPlayer,this.magicNumber));
     addToBot((AbstractGameAction) new DrawCardAction(this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MarkOfBalance();
   }
 }

