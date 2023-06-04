/*    */ package shadowverse.cards.Witch.Default;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ public class ConjureTwosome extends CustomCard {
/*    */   public static final String ID = "shadowverse:ConjureTwosome";
/* 17 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ConjureTwosome");
/* 18 */   public static final String NAME = cardStrings.NAME;
/* 19 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/ConjureTwosome.png";
/*    */   
/*    */   public ConjureTwosome() {
/* 23 */     super("shadowverse:ConjureTwosome", NAME, "img/cards/ConjureTwosome.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
/* 24 */     this.baseBlock = 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 29 */     if (!this.upgraded) {
/* 30 */       upgradeName();
/* 31 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 37 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
/* 38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return (AbstractCard)new ConjureTwosome();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\ConjureTwosome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */