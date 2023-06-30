 package shadowverse.cards.Dragon.Discard1;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Dragon;


 public class SpringwellDragonKeeper
   extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:SpringwellDragonKeeper";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SpringwellDragonKeeper");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SpringwellDragonKeeper.png";

   public SpringwellDragonKeeper() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY,2);
     this.baseDamage = 12;
     this.baseBlock = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeBlock(3);
     } 
   }


   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("SpringwellDragonKeeper_Eh"));
     addToBot(new DiscardAction(p,p,1,false));
     addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
     addToBot(new GainBlockAction(p,this.block));
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("SpringwellDragonKeeper"));
     addToBot(new DiscardAction(p,p,1,false));
     addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new SpringwellDragonKeeper();
   }
 }

