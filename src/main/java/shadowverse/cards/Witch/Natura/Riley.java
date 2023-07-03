 package shadowverse.cards.Witch.Natura;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.action.InvocationAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 
 
 
 
 
 
 
 public class Riley
   extends CustomCard
 {
   public static final String ID = "shadowverse:Riley";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Riley");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Riley.png";
   public static boolean dupCheck = true;
   
   public Riley() {
     super("shadowverse:Riley", NAME, "img/cards/Riley.png", 3, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 5;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
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
     int realBaseDamage = this.baseDamage;
     this.baseDamage += w.naterranCount * this.baseDamage;
     super.applyPowers();
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
       int count = ((AbstractShadowversePlayer) AbstractDungeon.player).naterranCount;
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
   }
   
   public void calculateCardDamage(AbstractMonster mo) {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     int realBaseDamage = this.baseDamage;
     this.baseDamage += w.naterranCount * this.baseDamage;
     super.calculateCardDamage(mo);
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }
   
   public void atTurnStart() {
     if (((AbstractShadowversePlayer)AbstractDungeon.player).naterranCount >= 7 && dupCheck) {
         dupCheck = false;
       if (AbstractDungeon.player.discardPile.contains(this)) {
         addToBot(new ReduceCostForTurnAction(this, 9));
         addToBot(new DiscardToHandAction(this));
       } else if (AbstractDungeon.player.drawPile.contains(this)) {
         addToBot(new ReduceCostForTurnAction(this, 9));
         addToBot(new InvocationAction(this));
       } 
     }else if (((AbstractShadowversePlayer)AbstractDungeon.player).naterranCount < 7){
       dupCheck = true;
     }
   }

     @Override
     public void triggerOnCardPlayed(AbstractCard cardPlayed) {
         dupCheck = true;
     }

     @Override
     public void triggerOnOtherCardPlayed(AbstractCard c) {
       dupCheck = true;
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Riley"));
     addToBot(new WaitAction(0.8F));
     addToBot(new SFXAction("ATTACK_HEAVY"));
     addToBot(new VFXAction(new LightningEffect(abstractMonster.drawX, abstractMonster.drawY), 0.05F));
     calculateCardDamage(abstractMonster);
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
   }
 
 
   
   public AbstractCard makeCopy() {
     return new Riley();
   }
 }


