/*    */ package shadowverse.cards.Witch.Earth1;
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
/*    */ import shadowverse.powers.EarthEssence;
/*    */ import shadowverse.powers.PiousInstructorPower;
/*    */ 
/*    */ public class PiousInstructor extends CustomCard {
/*    */   public static final String ID = "shadowverse:PiousInstructor";
/* 20 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PiousInstructor");
/* 21 */   public static final String NAME = cardStrings.NAME;
/* 22 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/PiousInstructor.png";
/*    */   
/*    */   public PiousInstructor() {
/* 26 */     super("shadowverse:PiousInstructor", NAME, "img/cards/PiousInstructor.png", 1, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
/* 27 */     this.baseMagicNumber = 4;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 42 */     addToBot((AbstractGameAction)new SFXAction("PiousInstructor"));
/* 43 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, 1), 1));
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new PiousInstructorPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new PiousInstructor();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\PiousInstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */