/*    */ package shadowverse.cards.Witch.Natura;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Elementalmana
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Elementalmana";
/* 20 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Elementalmana");
/* 21 */   public static final String NAME = cardStrings.NAME;
/* 22 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Elementalmana.png";
/*    */   
/*    */   public Elementalmana() {
/* 26 */     super("shadowverse:Elementalmana", NAME, "img/cards/Elementalmana.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
/* 27 */     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
/* 28 */     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
/* 29 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       this.exhaust = false;
/* 37 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 38 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 44 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 45 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return (AbstractCard)new Elementalmana();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\Elementalmana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */