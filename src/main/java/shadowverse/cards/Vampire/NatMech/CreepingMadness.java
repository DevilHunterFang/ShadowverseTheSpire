 package shadowverse.cards.Vampire.NatMech;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.NaterranTree;


 public class CreepingMadness extends CustomCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CreepingMadness");
   public static final String ID = "shadowverse:CreepingMadness";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CreepingMadness.png";

   public CreepingMadness() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new LoseHPAction(abstractPlayer,abstractPlayer,1));
       addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
       if (abstractPlayer.hasPower(NaterranTree.POWER_ID)){
           addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,2));
           addToBot((AbstractGameAction)new GainEnergyAction(1));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new CreepingMadness();
   }
 }

