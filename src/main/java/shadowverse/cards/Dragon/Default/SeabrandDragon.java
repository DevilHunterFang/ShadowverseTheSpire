 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class SeabrandDragon extends CustomCard {
   public static final String ID = "shadowverse:SeabrandDragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SeabrandDragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SeabrandDragon.png";

   public SeabrandDragon() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 12;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     int damage = this.damage;
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)) {
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0) {
         damage *= 2;
       }
     }
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SeabrandDragon();
   }
 }

