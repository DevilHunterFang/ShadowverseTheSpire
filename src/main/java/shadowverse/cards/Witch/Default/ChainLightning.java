/*    */ package shadowverse.cards.Witch.Default;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ public class ChainLightning
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:ChainLightning";
/* 20 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ChainLightning");
/* 21 */   public static final String NAME = cardStrings.NAME;
/* 22 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/ChainLightning.png";
/* 24 */   private static ChainLightning instance = new ChainLightning();
/*    */   
/*    */   public ChainLightning() {
/* 27 */     super("shadowverse:ChainLightning", NAME, "img/cards/ChainLightning.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
/* 28 */     this.baseDamage = 18;
/* 29 */     this.exhaust = true;
/* 30 */     this.cardsToPreview = (AbstractCard)instance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 36 */     if (!this.upgraded) {
/* 37 */       upgradeName();
/* 38 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 44 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 45 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
/* 46 */     if (abstractPlayer.hand.group.size() <= 4) {
/* 47 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new ChainLightning();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\ChainLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */