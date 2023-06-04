 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import shadowverse.characters.Witchcraft;


 public class NewEmber
   extends CustomCard {
   public static final String ID = "shadowverse:NewEmber";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewEmber");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NewEmber.png";

   public NewEmber() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
     this.baseDamage = 7;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
       ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
     }
     addToBot((AbstractGameAction)new SFXAction("NewEmber"));
     int drawPileAmt = abstractPlayer.drawPile.group.size();
     int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
     if (drawPileAmt <= masterDeckAmt/4){
       this.damage *=3;
     }else if (drawPileAmt <= masterDeckAmt/2){
       this.damage *= 2;
     }
     int leftDamage = this.damage;
     AbstractMonster weakestMonster = null;
     int amountOfweakestMonster = 0;
     while (leftDamage > 0) {
       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
         if (!m.isDead || !m.isDeadOrEscaped()) {
           if (weakestMonster == null) {
             weakestMonster = m;
             continue;
           }
           if (m.currentHealth < weakestMonster.currentHealth) {
             weakestMonster = m;
           }
         }
       }
       amountOfweakestMonster = weakestMonster.currentHealth + weakestMonster.currentBlock;
       if (leftDamage < amountOfweakestMonster) {
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
         System.out.println("大美伤害小于生命值最低怪物体力，大美已造成伤害");
         leftDamage = 0;
         continue;
       } else if (leftDamage >= amountOfweakestMonster) {
         System.out.println("当前生命值最低怪物体力与护盾的和为" + amountOfweakestMonster);
         int count = 0;
         for (AbstractMonster m2 : (AbstractDungeon.getMonsters()).monsters) {
           if (!m2.isDeadOrEscaped()) {
             count++;
           }
         }
         if (count <= 1) {
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
           System.out.println("大美伤害大于生命值最低怪物体力，当前房间只有一只怪物，大美已造成伤害");
           leftDamage = 0;
           continue;
         }
         System.out.println("大美伤害大于生命值最低怪物体力，当前房间有多只怪物");
         if (!weakestMonster.isDead || !weakestMonster.isDeadOrEscaped()) {
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, amountOfweakestMonster, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
           System.out.println("大美已杀死当前生命值最低怪物");
           leftDamage -= amountOfweakestMonster;
           System.out.println("大美伤害值剩余" + leftDamage);
           (AbstractDungeon.getMonsters()).monsters.remove(weakestMonster);
           weakestMonster = null;
         }
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NewEmber();
   }
 }

