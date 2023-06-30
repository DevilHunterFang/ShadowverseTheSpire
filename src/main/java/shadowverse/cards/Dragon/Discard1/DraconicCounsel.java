 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.DraconicCounsel_Copy;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class DraconicCounsel
   extends CustomCard {
   public static final String ID = "shadowverse:DraconicCounsel";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DraconicCounsel");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DraconicCounsel.png";

   public DraconicCounsel() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new DraconicCounsel_Copy();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       this.cardsToPreview.upgrade();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("DraconicCounsel"));
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
     addToBot(new DrawCardAction(this.magicNumber));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0){
         addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DraconicCounsel();
   }
 }

