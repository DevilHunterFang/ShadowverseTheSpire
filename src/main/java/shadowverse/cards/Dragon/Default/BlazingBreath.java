 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;


 public class BlazingBreath extends CustomCard {
   public static final String ID = "shadowverse:BlazingBreath";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlazingBreath");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BlazingBreath.png";

   public BlazingBreath() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 4;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BlazingBreath();
   }
 }

