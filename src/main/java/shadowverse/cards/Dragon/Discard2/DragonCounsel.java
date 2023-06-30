 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DiscardRandomMinimumAction;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class DragonCounsel
   extends CustomCard {
   public static final String ID = "shadowverse:DragonCounsel";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonCounsel");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DragonCounsel.png";

   public DragonCounsel() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DiscardRandomMinimumAction(abstractPlayer,abstractPlayer,1));
     addToBot(new DrawCardAction(this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DragonCounsel();
   }
 }

