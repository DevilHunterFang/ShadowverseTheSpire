 package shadowverse.cards.Bishop.Recover2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.PureflowerMaidenPower;


 public class PureflowerMaiden
   extends CustomCard {
   public static final String ID = "shadowverse:PureflowerMaiden";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PureflowerMaiden");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PureflowerMaiden.png";

   public PureflowerMaiden() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 9;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("PureflowerMaiden"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new PureflowerMaidenPower(abstractPlayer)));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PureflowerMaiden();
   }
 }

