 package shadowverse.cards.Bishop.Amulet2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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


 public class PunishmentSniper extends CustomCard {
   public static final String ID = "shadowverse:PunishmentSniper";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PunishmentSniper");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PunishmentSniper.png";

   public PunishmentSniper() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseBlock = 8;
     this.baseMagicNumber = 5;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 0;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(2);
     } 
   }

   public void applyPowers() {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
     if(w.amuletCount > 0){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = (w.amuletCount%2==0?w.amuletCount/2:w.amuletCount/2+1) * this.magicNumber;
       super.applyPowers();
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }
   }

   public void calculateCardDamage(AbstractMonster mo) {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     int realBaseDamage = this.baseDamage;
     this.baseDamage = (w.amuletCount%2==0?w.amuletCount/2:w.amuletCount/2+1) * this.magicNumber;
     super.calculateCardDamage(mo);
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("PunishmentSniper"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.baseBlock));
     calculateCardDamage(abstractMonster);
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PunishmentSniper();
   }
 }

