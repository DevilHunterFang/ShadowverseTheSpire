 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class ScaleboundPlight
   extends CustomCard {
   public static final String ID = "shadowverse:ScaleboundPlight";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ScaleboundPlight");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ScaleboundPlight.png";

   public ScaleboundPlight() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.NONE);
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
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,this.magicNumber,false));
     addToBot(new DrawCardAction(this.magicNumber));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0){
         addToBot(new DrawCardAction(1));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ScaleboundPlight();
   }
 }

