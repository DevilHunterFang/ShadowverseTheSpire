 package shadowverse.cards.Vampire.Bat;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ForestBat;
import shadowverse.characters.Vampire;


 public class SummoningBloodkin extends CustomCard {
   public static final String ID = "shadowverse:SummoningBloodkin";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SummoningBloodkin");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SummoningBloodkin.png";

   public SummoningBloodkin() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new ForestBat();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SummoningBloodkin();
   }
 }

