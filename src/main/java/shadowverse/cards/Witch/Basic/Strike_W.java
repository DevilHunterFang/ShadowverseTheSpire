/*    */ package shadowverse.cards.Witch.Basic;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ public class Strike_W extends CustomCard {
/*    */   public static final String ID = "shadowverse:Strike_W";
/* 17 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Strike_W");
/* 18 */   public static final String NAME = cardStrings.NAME;
/* 19 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Strike_W.png";
/*    */   
/*    */   public Strike_W() {
/* 23 */     super("shadowverse:Strike_W", NAME, "img/cards/Strike_W.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.BASIC, CardTarget.ENEMY);
/* 24 */     this.baseDamage = 6;
/* 25 */     this.tags.add(CardTags.STRIKE);
/* 26 */     this.tags.add(CardTags.STARTER_STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 31 */     if (!this.upgraded) {
/* 32 */       upgradeName();
/* 33 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 39 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new Strike_W();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Basic\Strike_W.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */