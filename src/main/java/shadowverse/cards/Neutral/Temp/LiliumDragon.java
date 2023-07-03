 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.*;
 import shadowverse.characters.Dragon;


 public class LiliumDragon
   extends CustomCard
 {
   public static final String ID = "shadowverse:LiliumDragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LiliumDragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LiliumDragon.png";

   public LiliumDragon() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 15;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
       if (abstractMonster.hasPower("Artifact")) {
           addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractPlayer, "Artifact"));
       } else {
           for (AbstractPower pow : abstractMonster.powers) {
               if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower") {
                   addToBot(new RemoveSpecificPowerAction(pow.owner, abstractPlayer, pow.ID));
                   break;
               }
           }
       }
       addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new StrengthPower(abstractMonster, -3), -3));
       if (!abstractMonster.hasPower("Artifact"))
           addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new GainStrengthPower(abstractMonster, 3), 3));
   }
 
   
   public AbstractCard makeCopy() {
     return new LiliumDragon();
   }
 }

