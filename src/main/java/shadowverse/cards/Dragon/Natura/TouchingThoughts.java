 package shadowverse.cards.Dragon.Natura;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class TouchingThoughts
   extends CustomCard {
   public static final String ID = "shadowverse:TouchingThoughts";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TouchingThoughts");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TouchingThoughts.png";

   public TouchingThoughts() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("TouchingThoughts"));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),this.magicNumber));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0){
         addToBot(new GainEnergyAction(1));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TouchingThoughts();
   }
 }

