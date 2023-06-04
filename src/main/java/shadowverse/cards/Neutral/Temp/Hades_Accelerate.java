 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Necromancer;


 public class Hades_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:Hades_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HadesElemental");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Hades.png";

   public Hades_Accelerate() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
     this.baseDamage = 16;
       this.isMultiDamage = true;
       this.baseMagicNumber = 6;
       this.magicNumber = this.baseMagicNumber;
       this.baseBlock = 40;
       this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
         upgradeDamage(6);
         upgradeBlock(10);
     } 
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Hades_Acc"));
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Hades_Accelerate();
   }
 }


