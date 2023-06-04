 package shadowverse.cards.Vampire.Bat;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;


 public class DarkfeastBat
   extends CustomCard
 {
   public static final String ID = "shadowverse:DarkfeastBat";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DarkfeastBat");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DarkfeastBat.png";

   public DarkfeastBat() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }


   public void applyPowers() {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     if(w.wrathCount > 0){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = w.wrathCount * this.baseDamage;
       super.applyPowers();
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }
   }
   
   public void calculateCardDamage(AbstractMonster mo) {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     int realBaseDamage = this.baseDamage;
     this.baseDamage = w.wrathCount * this.baseDamage;
     super.calculateCardDamage(mo);
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new WaitAction(0.6F));
     calculateCardDamage(abstractMonster);
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(abstractMonster.hb.cX, abstractMonster.hb.cY), 0.1F));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DarkfeastBat();
   }
 }


