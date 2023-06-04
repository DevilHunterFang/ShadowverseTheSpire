 package shadowverse.cards.Witch.Natura;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Horse;
import shadowverse.cards.Neutral.Temp.RapidFire;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


 public class Maiser
   extends CustomCard {
   public static final String ID = "shadowverse:Maiser";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Maiser");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Maiser.png";

   public Maiser() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new RapidFire();
     this.tags.add(AbstractShadowversePlayer.Enums.HERO);
   }
 

 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Maiser"));
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       AbstractCard a = (AbstractCard)new Horse();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(a, 1));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Maiser();
   }
 }

