/*    */ package shadowverse.cards.Witch.Spellboost2;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EdictOfTruth
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:EdictOfTruth";
/* 22 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EdictOfTruth");
/* 23 */   public static final String NAME = cardStrings.NAME;
/* 24 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/EdictOfTruth.png";
/*    */   
/*    */   public EdictOfTruth() {
/* 28 */     super("shadowverse:EdictOfTruth", NAME, "img/cards/EdictOfTruth.png", 3, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
/* 29 */     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
/* 30 */     this.baseMagicNumber = 0;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/* 32 */     this.exhaust = true;
/* 33 */     this.selfRetain = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnOtherCardPlayed(AbstractCard c) {
/* 38 */     if (c.type == CardType.SKILL) {
/* 39 */       flash();
/* 40 */       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/*    */       
/* 42 */       this.magicNumber = ++this.baseMagicNumber;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeBaseCost(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 57 */     CardCrawlGame.sound.playA("EdictOfTruth", 0.0F);
/* 58 */     addToBot((AbstractGameAction)new ExpertiseAction((AbstractCreature)abstractPlayer, 10));
/* 59 */     if (this.magicNumber >= 6) {
/* 60 */       addToBot((AbstractGameAction)new GainEnergyAction(2));
/*    */     }
/* 62 */     this.baseMagicNumber = 0;
/* 63 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void applyPowers() {
/* 67 */     super.applyPowers();
/* 68 */     int count = this.magicNumber;
/* 69 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 70 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/* 71 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/* 72 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 77 */     return (AbstractCard)new EdictOfTruth();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\EdictOfTruth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */