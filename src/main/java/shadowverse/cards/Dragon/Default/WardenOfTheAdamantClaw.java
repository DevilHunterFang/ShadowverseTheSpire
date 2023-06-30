 package shadowverse.cards.Dragon.Default;
 


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class WardenOfTheAdamantClaw extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:WardenOfTheAdamantClaw";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfTheAdamantClaw");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WardenOfTheAdamantClaw.png";

   public WardenOfTheAdamantClaw() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF,2);
     this.baseBlock = 4;
     this.baseMagicNumber = 9;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeMagicNumber(2);
     } 
   }

   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new GainBlockAction(p,p,this.block));
     addToBot(new GainBlockAction(p,p,this.magicNumber));
     addToBot(new DrawCardAction(3));
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new GainBlockAction(p,p,this.block));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new WardenOfTheAdamantClaw();
   }
 }

