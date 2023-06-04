 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Witchcraft;


 public class PerjuryOfTruth
   extends CustomCard {
   public static final String ID = "shadowverse:PerjuryOfTruth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PerjuryOfTruth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PerjuryOfTruth.png";

   public PerjuryOfTruth() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("PerjuryOfTruth"));
     if (null != abstractMonster.getPower(StrengthPower.POWER_ID) && abstractMonster.getPower(StrengthPower.POWER_ID).amount>0){
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,StrengthPower.POWER_ID));
     }
     if (null != abstractMonster.getPower(DexterityPower.POWER_ID) && abstractMonster.getPower(DexterityPower.POWER_ID).amount>0){
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(abstractMonster,abstractPlayer,DexterityPower.POWER_ID));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PerjuryOfTruth();
   }
 }

