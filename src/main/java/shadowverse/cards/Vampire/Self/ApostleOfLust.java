 package shadowverse.cards.Vampire.Self;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.LoseStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.characters.Vampire;


 public class ApostleOfLust
   extends CustomCard
 {
   public static final String ID = "shadowverse:ApostleOfLust";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ApostleOfLust");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ApostleOfLust.png";

   public ApostleOfLust() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ALL);
     this.baseBlock = 15;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("ApostleOfLust"));
       addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
       addToBot((AbstractGameAction)new DrawCardAction(2));
       addToBot((AbstractGameAction)new LoseHPAction(p,p,2));
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           if (!mo.isDeadOrEscaped()){
               addToBot((AbstractGameAction)new LoseHPAction(mo,p,1));
               addToBot((AbstractGameAction)new ApplyPowerAction(mo, p, (AbstractPower)new StrengthPower(mo, 1), 1));
               addToBot((AbstractGameAction)new ApplyPowerAction(mo, p, (AbstractPower)new LoseStrengthPower(mo, 1), 1));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ApostleOfLust();
   }
 }

