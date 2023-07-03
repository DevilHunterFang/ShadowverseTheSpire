 package shadowverse.cards.Dragon.Buff;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Dragon;


 public class Sandstorm
   extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:Sandstorm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Sandstorm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Sandstorm.png";
   private boolean trigger;
   
   public Sandstorm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ALL,1);
     this.baseMagicNumber = 8;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(3);
     } 
   }

   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     if (!trigger){
       trigger = true;
       addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
     }
     addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     if (!trigger){
       trigger = true;
       addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
     }
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new Sandstorm();
   }
 }

