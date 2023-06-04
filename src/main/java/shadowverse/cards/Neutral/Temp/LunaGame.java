 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.Necromancer;


 public class LunaGame
   extends CustomCard
 {
   public static final String ID = "shadowverse:LunaGame";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LunaGame");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LunaGame.png";

   public LunaGame() {
     super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     onChoseThisOption();
   }
   
   public void onChoseThisOption() {
     addToBot((AbstractGameAction)new SFXAction("LunaGame"));
     addToBot((AbstractGameAction)new ReanimateAction(2));
       addToBot((AbstractGameAction)new ReanimateAction(1));
       addToBot((AbstractGameAction)new ReanimateAction(0));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LunaGame();
   }
 }

