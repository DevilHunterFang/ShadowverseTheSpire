 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;


 public class DragonewtMight
   extends CustomCard {
   public static final String ID = "shadowverse:DragonewtMight";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonewtMight");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DragonewtMight.png";

   public DragonewtMight() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ALL_ENEMY);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       upgradeDamage(3);
     } 
   }

   @Override
   public void triggerOnManualDiscard() {
     addToBot(new DrawCardAction(this.magicNumber));
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
     addToBot(new DrawCardAction(1));
     addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DragonewtMight();
   }
 }

