 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.DragonTagPower;
import shadowverse.powers.OverflowPower;


 public class Georgius extends CustomCard {
   public static final String ID = "shadowverse:Georgius";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Georgius");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Georgius.png";

   public Georgius() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ALL);
     this.baseDamage = 15;
     this.isMultiDamage = true;
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Georgius"));
     addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)) {
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0) {
         addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
       }
       if (p.amount2 > 1){
         addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
       }
     }
     for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
       if (m != null && !m.isDeadOrEscaped()){
         addToBot(new ApplyPowerAction(m,abstractPlayer,new DragonTagPower(m)));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Georgius();
   }
 }

