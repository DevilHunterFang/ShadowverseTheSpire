/*    */ package shadowverse.cards.Witch.Basic;
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
/*    */ public class Defend_W
/*    */   extends CustomCard {
/*    */   public static final String ID = "shadowverse:Defend_W";
/* 18 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Defend_W");
/* 19 */   public static final String NAME = cardStrings.NAME;
/* 20 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Defend_W.png";
/*    */   
/*    */   public Defend_W() {
/* 24 */     super("shadowverse:Defend_W", NAME, "img/cards/Defend_W.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.BASIC, CardTarget.SELF);
/* 25 */     this.baseBlock = 5;
/* 26 */     this.tags.add(CardTags.STARTER_DEFEND);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 31 */     if (!this.upgraded) {
/* 32 */       upgradeName();
/* 33 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 39 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 45 */     return (AbstractCard)new Defend_W();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Basic\Defend_W.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */