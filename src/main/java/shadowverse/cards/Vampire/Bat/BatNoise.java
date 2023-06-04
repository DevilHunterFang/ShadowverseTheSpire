 package shadowverse.cards.Vampire.Bat;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ForestBat;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;


 public class BatNoise
   extends CustomCard
 {
   public static final String ID = "shadowverse:BatNoise";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BatNoise");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BatNoise.png";

   public BatNoise() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.exhaust = true;
     this.cardsToPreview = (AbstractCard)new ForestBat();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       if (p.hasPower(EpitaphPower.POWER_ID)||p.hasPower(WrathPower.POWER_ID)){
           c.upgrade();
           addToBot((AbstractGameAction)new DrawCardAction(1));
       }else {
           addToBot((AbstractGameAction)new LoseHPAction(p,p,1));
       }
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BatNoise();
   }
 }

