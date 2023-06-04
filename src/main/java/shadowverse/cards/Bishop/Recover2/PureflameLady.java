 package shadowverse.cards.Bishop.Recover2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.PureflameLadyPower;


 public class PureflameLady
   extends CustomCard {
   public static final String ID = "shadowverse:PureflameLady";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PureflameLady");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PureflameLady.png";

   public PureflameLady() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 3;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("PureflameLady"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new PureflameLadyPower(abstractPlayer)));
     addToBot(new HealAction(abstractPlayer,abstractPlayer,1));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PureflameLady();
   }
 }

