 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.action.NecromanceAction;
 import shadowverse.action.TemptationAction;
 import shadowverse.characters.Necromancer;


 public class NecroTemptation extends CustomCard {
   public static final String ID = "shadowverse:NecroTemptation";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NecroTemptation");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NecroTemptation.png";

   public NecroTemptation() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }

 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("NecroTemptation"));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
       addToBot((AbstractGameAction)new NecromanceAction(8,null,(AbstractGameAction)new TemptationAction(abstractMonster)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NecroTemptation();
   }
 }

