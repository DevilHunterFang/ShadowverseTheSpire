/*    */ package shadowverse.cards.Witch.Mech;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.AttackFromDeckToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.IsabellesConjuration;
import shadowverse.cards.Neutral.Temp.TetrasMettle;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.MachineDrawPower;

import java.util.ArrayList;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SorceryInSolidarity
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:SorceryInSolidarity";
/* 28 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SorceryInSolidarity");
/* 29 */   public static final String NAME = cardStrings.NAME;
/* 30 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/SorceryInSolidarity.png";
/*    */   
/*    */   public SorceryInSolidarity() {
/* 34 */     super("shadowverse:SorceryInSolidarity", NAME, "img/cards/SorceryInSolidarity.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
/* 35 */     this.baseMagicNumber = 0;
/* 36 */     this.magicNumber = this.baseMagicNumber;
/* 37 */     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
/* 38 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void triggerOnOtherCardPlayed(AbstractCard c) {
/* 50 */     if (c.type == CardType.SKILL) {
/* 51 */       flash();
/*    */       
/* 53 */       this.magicNumber = ++this.baseMagicNumber;
/* 54 */       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 60 */     if (this.magicNumber >= 3) {
/* 61 */       addToBot((AbstractGameAction)new SFXAction("SorceryInSolidarity"));
/* 62 */       addToBot((AbstractGameAction)new AttackFromDeckToHandAction(1));
/* 63 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new MachineDrawPower((AbstractCreature)abstractPlayer, 1), 1));
/*    */     } else {
/* 65 */       ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
/* 66 */       stanceChoices.add(new IsabellesConjuration());
/* 67 */       stanceChoices.add(new TetrasMettle());
/* 68 */       addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
/*    */     } 
/* 70 */     this.baseMagicNumber = 0;
/* 71 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 76 */     super.applyPowers();
/* 77 */     int count = this.magicNumber;
/* 78 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 79 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/* 80 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/* 81 */     initializeDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 87 */     return (AbstractCard)new SorceryInSolidarity();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\SorceryInSolidarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */