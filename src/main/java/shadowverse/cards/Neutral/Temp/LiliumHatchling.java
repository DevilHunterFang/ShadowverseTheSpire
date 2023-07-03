 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Dragon;


 public class LiliumHatchling
   extends CustomCard
 {
   public static final String ID = "shadowverse:LiliumHatchling";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LiliumHatchling");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LiliumHatchling.png";

   public LiliumHatchling() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 3;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new HealAction(abstractPlayer,abstractPlayer,2));
   }
 
   
   public AbstractCard makeCopy() {
     return new LiliumHatchling();
   }
 }

