 package shadowverse.cards.Dragon.Buff;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.characters.Dragon;


 public class WindsweptDragonewt
   extends CustomCard {
   public static final String ID = "shadowverse:WindsweptDragonewt";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WindsweptDragonewt");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WindsweptDragonewt.png";

   public WindsweptDragonewt() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }

   @Override
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("WindsweptDragonewt"));
     int dmg = this.damage;
     if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 2){
       dmg *= 2;
     }
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,dmg,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
   }



   public AbstractCard makeCopy() {
     return (AbstractCard)new WindsweptDragonewt();
   }
 }

