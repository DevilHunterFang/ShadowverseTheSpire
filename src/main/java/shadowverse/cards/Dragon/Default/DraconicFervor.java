 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class DraconicFervor
   extends CustomCard {
   public static final String ID = "shadowverse:DraconicFervor";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DraconicFervor");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DraconicFervor.png";

   public DraconicFervor() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new EnergizedPower(abstractPlayer,this.magicNumber)));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new DrawCardAction(this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DraconicFervor();
   }
 }

