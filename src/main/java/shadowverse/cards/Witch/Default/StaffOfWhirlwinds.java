/*    */ package shadowverse.cards.Witch.Default;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
/*    */ import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ 
/*    */ public class StaffOfWhirlwinds
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:StaffOfWhirlwinds";
/* 25 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StaffOfWhirlwinds");
/* 26 */   public static final String NAME = cardStrings.NAME;
/* 27 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/StaffOfWhirlwinds.png";
/*    */   
/*    */   public StaffOfWhirlwinds() {
/* 31 */     super("shadowverse:StaffOfWhirlwinds", NAME, "img/cards/StaffOfWhirlwinds.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
/* 32 */     this.baseDamage = 12;
/* 33 */     this.baseMagicNumber = this.baseDamage / 3;
/* 34 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 47 */     addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIRLWIND"));
/* 48 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.0F));
/* 49 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 50 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new StaffOfWhirlwinds();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\StaffOfWhirlwinds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */