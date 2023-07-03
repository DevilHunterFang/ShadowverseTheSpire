 package shadowverse.cards.Dragon.Tempo;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class HypersonicDragonewt extends CustomCard {
   public static final String ID = "shadowverse:HypersonicDragonewt";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HypersonicDragonewt");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HypersonicDragonewt.png";
   
   public HypersonicDragonewt() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("HypersonicDragonewt"));
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0){
         addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HypersonicDragonewt();
   }
 }

