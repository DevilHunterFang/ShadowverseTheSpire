 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class PlatinumDragonblader
   extends CustomCard {
   public static final String ID = "shadowverse:PlatinumDragonblader";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PlatinumDragonblader");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PlatinumDragonblader.png";

   public PlatinumDragonblader() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 3;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("PlatinumDragonblader"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 1){
         addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new PenNibPower(abstractPlayer,1),1));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PlatinumDragonblader();
   }
 }

