 package shadowverse.cards.Dragon.Buff;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import shadowverse.characters.Dragon;


 public class CelestialDragoon
   extends CustomCard {
   public static final String ID = "shadowverse:CelestialDragoon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CelestialDragoon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CelestialDragoon.png";

   public CelestialDragoon() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseBlock = 3;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   @Override
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("CelestialDragoon"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,1),1));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,1),1));
     if (upgraded){
       addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.block,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
       addToBot(new VFXAction(new ClashEffect(abstractMonster.hb.cX, abstractMonster.hb.cY), 0.1F));
     }
   }



   public AbstractCard makeCopy() {
     return (AbstractCard)new CelestialDragoon();
   }
 }

