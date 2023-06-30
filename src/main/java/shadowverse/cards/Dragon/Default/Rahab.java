 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.RahabPower;

 public class Rahab extends CustomCard {
   public static final String ID = "shadowverse:Rahab";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Rahab");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Rahab.png";

   public Rahab() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 12;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RahabPower(abstractPlayer,1),1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Rahab();
   }
 }

