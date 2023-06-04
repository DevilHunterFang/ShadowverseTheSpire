 package shadowverse.cards.Bishop.Recover2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


 public class MarkUnleashed extends CustomCard {
   public static final String ID = "shadowverse:MarkUnleashed";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MarkUnleashed");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MarkUnleashed.png";
   private boolean triggered;

   public MarkUnleashed() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 2;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
       upgradeDamage(2);
     } 
   }

   public void applyPowers() {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
     if (w.healCount>0){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = w.healCount * this.magicNumber;
       super.applyPowers();
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }
   }

   public void calculateCardDamage(AbstractMonster mo) {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     if (w.healCount>0){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = w.healCount * this.magicNumber;
       super.calculateCardDamage(mo);
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction) new SFXAction("MarkUnleashed"));
     if (!this.triggered){
       addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,1));
       this.triggered = true;
     }
     calculateCardDamage(abstractMonster);
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MarkUnleashed();
   }
 }

