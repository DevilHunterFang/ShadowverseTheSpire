/*    */ package shadowverse.cards.Witch.Earth2;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.ErasmusPower;
/*    */ 
/*    */ public class Erasmus
/*    */   extends CustomCard {
/*    */   public static final String ID = "shadowverse:Erasmus";
/* 20 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Erasmus");
/* 21 */   public static final String NAME = cardStrings.NAME;
/* 22 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Erasmus.png";
/*    */   
/*    */   public Erasmus() {
/* 26 */     super("shadowverse:Erasmus", NAME, "img/cards/Erasmus.png", 2, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
/* 27 */     this.baseMagicNumber = 7;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeMagicNumber(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 42 */     addToBot((AbstractGameAction)new SFXAction("Erasmus"));
/* 43 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ErasmusPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return (AbstractCard)new Erasmus();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Rare\Erasmus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */