/*    */ package shadowverse.cards.Neutral.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InfernalSurge
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:InfernalSurge";
/* 25 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:InfernalSurge");
/* 26 */   public static final String NAME = cardStrings.NAME;
/* 27 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/InfernalSurge.png";
/*    */   
/*    */   public InfernalSurge() {
/* 31 */     super("shadowverse:InfernalSurge", NAME, "img/cards/InfernalSurge.png", 0, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
/* 32 */     this.baseMagicNumber = 3;
/* 33 */     this.magicNumber = this.baseMagicNumber;
/* 34 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 47 */     addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new InflameEffect((AbstractCreature)abstractPlayer), 1.0F));
/* 48 */     addToBot((AbstractGameAction)new SFXAction("InfernalSurge"));
/* 49 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return (AbstractCard)new InfernalSurge();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\InfernalSurge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */