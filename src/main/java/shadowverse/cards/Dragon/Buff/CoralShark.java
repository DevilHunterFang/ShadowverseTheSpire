 package shadowverse.cards.Dragon.Buff;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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


 public class CoralShark
   extends CustomCard {
   public static final String ID = "shadowverse:CoralShark";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CoralShark");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CoralShark.png";
   
   public CoralShark() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
     this.baseBlock = 5;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeBlock(2);
     } 
   }

   @Override
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 2){
       addToBot(new GainBlockAction(abstractPlayer,this.block));
     }
   }



   public AbstractCard makeCopy() {
     return (AbstractCard)new CoralShark();
   }
 }

