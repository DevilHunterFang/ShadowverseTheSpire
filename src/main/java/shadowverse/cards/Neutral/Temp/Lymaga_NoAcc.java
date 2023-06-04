 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.InvocationAction;
import shadowverse.cards.Elf.Wood.GreenWoodGuardian;
import shadowverse.characters.Elf;


 public class Lymaga_NoAcc
   extends CustomCard
 {
   public static final String ID = "shadowverse:Lymaga_NoAcc";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lymaga_NoAcc");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lymaga.png";
   public static boolean dupCheck = true;

   public Lymaga_NoAcc() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 32;
     this.cardsToPreview = (AbstractCard)new GreenWoodGuardian();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(10);
     } 
   }

   
   public void applyPowers() {
       super.applyPowers();
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
           if (c instanceof GreenWoodGuardian)
               count++;
       }
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
   }

   
   public void atTurnStart() {
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
           if (c instanceof GreenWoodGuardian)
               count++;
       }
     if (count >= 6 && dupCheck) {
         dupCheck = false;
       if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new DiscardToHandAction((AbstractCard)this));
       } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new InvocationAction((AbstractCard)this));
       } 
     }else if (count < 6){
       dupCheck = true;
     }
   }

     public void triggerOnOtherCardPlayed(AbstractCard c){
         dupCheck = true;
     }

     @Override
     public void triggerOnCardPlayed(AbstractCard cardPlayed) {
         dupCheck = true;
     }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
           addToBot((AbstractGameAction)new SFXAction("Lymaga"));
           addToBot((AbstractGameAction)new WaitAction(0.8F));
           addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lymaga_NoAcc();
   }
 }


