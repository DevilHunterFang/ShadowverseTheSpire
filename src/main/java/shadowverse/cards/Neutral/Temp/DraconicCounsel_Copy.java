 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class DraconicCounsel_Copy
   extends CustomCard {
   public static final String ID = "shadowverse:DraconicCounsel_Copy";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DraconicCounsel_Copy");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DraconicCounsel.png";

   public DraconicCounsel_Copy() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("DraconicCounsel"));
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
     addToBot(new DrawCardAction(this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DraconicCounsel_Copy();
   }
 }

