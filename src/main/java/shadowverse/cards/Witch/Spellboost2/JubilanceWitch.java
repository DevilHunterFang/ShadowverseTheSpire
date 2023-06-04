 package shadowverse.cards.Witch.Spellboost2;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;


 public class JubilanceWitch extends CustomCard {
   public static final String ID = "shadowverse:JubilanceWitch";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JubilanceWitch");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/JubilanceWitch.png";

   public JubilanceWitch() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ALL);
     this.baseDamage = 9;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.isMultiDamage = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("JubilanceWitch"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
     AbstractPower p = abstractPlayer.getPower(EarthEssence.POWER_ID);
     if (p!=null&&(p.amount+this.magicNumber)>2){
       addToBot(new DamageAllEnemiesAction(abstractPlayer,this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new JubilanceWitch();
   }
 }


