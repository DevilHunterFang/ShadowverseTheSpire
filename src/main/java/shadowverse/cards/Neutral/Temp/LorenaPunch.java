 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Bishop;


 public class LorenaPunch extends CustomCard {
   public static final String ID = "shadowverse:LorenaPunch";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LorenaPunch");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LorenaPunch.png";

   public LorenaPunch() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 8;
     this.baseBlock = 8;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeBlock(2);
     } 
   }

   public void applyPowers() {
     AbstractPower str = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
     AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
     int strAmt = str==null?0:str.amount;
     int dexAmt = dex==null?0:dex.amount;
     if (strAmt<dexAmt){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = this.baseDamage+dexAmt;
       if (strAmt!=0)
         this.baseDamage-=strAmt;
       super.applyPowers();
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }else{
       super.applyPowers();
     }
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction) new SFXAction("LorenaPunch"));
     AbstractPower str = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
     AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
     int strAmt = str==null?0:str.amount;
     int dexAmt = dex==null?0:dex.amount;
     if (strAmt<dexAmt){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.block, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }else {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LorenaPunch();
   }
 }

