 package shadowverse.cards.Elf.Left;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.characters.Elf;


 public class MiracleHarvest
   extends CustomCard
 {
   public static final String ID = "shadowverse:MiracleHarvest";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MiracleHarvest");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MiracleHarvest.png";
   private boolean triggered;

   public MiracleHarvest() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.cardsToPreview = new Fairy();
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }

   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractCard f = this.cardsToPreview.makeStatEquivalentCopy();
       int amt = 0;
       for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
           if (c instanceof Fairy)
               amt++;
       }
       if (amt>=5)
           f.upgrade();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(f));
       if (amt>=10){
           addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
           if (!this.triggered){
               this.triggered = true;
               addToBot((AbstractGameAction)new GainEnergyAction(this.magicNumber));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MiracleHarvest();
   }
 }

