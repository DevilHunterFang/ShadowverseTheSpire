 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Bishop;


 public class Selena_Story
   extends CustomCard {
   public static final String ID = "shadowverse:Selena_Story";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Selena_Story");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Selena.png";

   public Selena_Story() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 12;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(6);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     int atkAmt = 0;
     addToBot((AbstractGameAction)new SFXAction("Selena_Story"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     for (AbstractCard c:abstractPlayer.hand.group){
       if (c.type == CardType.ATTACK)
         atkAmt++;
     }
     if (atkAmt>3){
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,1),1));
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,1),1));
     }
     if (atkAmt>2){
       addToBot((AbstractGameAction)new DrawCardAction(2));
     }
     if (atkAmt>1){
       addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,4));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Selena_Story();
   }
 }

