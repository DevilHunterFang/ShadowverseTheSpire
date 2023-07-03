 package shadowverse.cards.Dragon.Buff;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Dragon;


 public class WhimsicalMermaid
   extends CustomCard {
   public static final String ID = "shadowverse:WhimsicalMermaid";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WhimsicalMermaid");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WhimsicalMermaid.png";
   private boolean trigger;
   
   public WhimsicalMermaid() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }

   @Override
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("WhimsicalMermaid"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     int amt = 1;
     if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 2){
       amt++;
     }
     addToBot(new DrawCardAction(amt));
     if (!trigger){
       trigger = true;
       addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,1),1));
     }
   }



   public AbstractCard makeCopy() {
     return (AbstractCard)new WhimsicalMermaid();
   }
 }

