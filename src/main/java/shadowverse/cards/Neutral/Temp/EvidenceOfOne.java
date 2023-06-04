 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.powers.OmenOfOnePower2;


 public class EvidenceOfOne
   extends CustomCard
 {
   public static final String ID = "shadowverse:EvidenceOfOne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EvidenceOfOne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/EvidenceOfOne.png";

   public EvidenceOfOne() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 18;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeMagicNumber(1);
     } 
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("EvidenceOfOne"));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,new VulnerablePower(abstractMonster,this.magicNumber,false),this.magicNumber));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,new WeakPower(abstractMonster,this.magicNumber,false),this.magicNumber));
     if (abstractPlayer.hasPower(OmenOfOnePower2.POWER_ID)){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature) abstractMonster, new DamageInfo(abstractPlayer,this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new EvidenceOfOne();
   }
 }

