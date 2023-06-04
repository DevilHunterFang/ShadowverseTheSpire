 package shadowverse.cards.Elf.Left;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;

 
 
 public class Bayle
   extends CustomCard
 {
   public static final String ID = "shadowverse:Bayle";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Bayle");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Bayle.png";
   public static  final  int BASE_COST = 5;
   
   public Bayle() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 20;
   }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (c.type == CardType.ATTACK) {
             flash();
             addToBot((AbstractGameAction)new SFXAction("spell_boost"));
             addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
         }
     }
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Bayle"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
       this.cost = BASE_COST;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Bayle();
   }
 }

