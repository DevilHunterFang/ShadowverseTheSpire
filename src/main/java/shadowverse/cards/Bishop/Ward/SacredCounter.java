 package shadowverse.cards.Bishop.Ward;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Bishop;


 public class SacredCounter extends CustomCard {
   public static final String ID = "shadowverse:SacredCounter";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SacredCounter");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SacredCounter.png";

   public SacredCounter() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 0;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }

   public void applyPowers() {
     this.baseDamage = AbstractDungeon.player.currentBlock;
     super.applyPowers();
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
     initializeDescription();
   }

   public void onMoveToDiscard() {
     this.rawDescription = cardStrings.DESCRIPTION;
     initializeDescription();
   }

   public void calculateCardDamage(AbstractMonster mo) {
     super.calculateCardDamage(mo);
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
     initializeDescription();
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
     this.baseDamage = p.currentBlock;
     if (m != null && m.getIntentBaseDmg()>0 && this.baseDamage>m.getIntentDmg()){
       calculateCardDamage(m);
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     }
     this.rawDescription = cardStrings.DESCRIPTION;
     initializeDescription();
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SacredCounter();
   }
 }

