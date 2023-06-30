 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.NightVampirePower;
import shadowverse.powers.VanpiPower;
import shadowverse.powers.WrathPower;


 public class ForestBat
   extends CustomCard {
   public static final String ID = "shadowverse:ForestBat";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ForestBat");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ForestBat.png";

   public ForestBat() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.ENEMY);
     if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("shadowverse:OldBloodKingPower")){
       this.baseDamage = 4 + (AbstractDungeon.player.getPower("shadowverse:OldBloodKingPower")).amount;
     } else {
       this.baseDamage = 4;
     }
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
     } 
   }

   public void applyPowers() {
     if(AbstractDungeon.player.hasPower(NightVampirePower.POWER_ID) || AbstractDungeon.player.hasPower(VanpiPower.POWER_ID)){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = this.baseDamage * 2;
       super.applyPowers();
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }else {
       super.applyPowers();
     }
   }

   public void calculateCardDamage(AbstractMonster mo) {
     if(AbstractDungeon.player.hasPower(NightVampirePower.POWER_ID) || AbstractDungeon.player.hasPower(VanpiPower.POWER_ID)){
       int realBaseDamage = this.baseDamage;
       this.baseDamage = this.baseDamage * 2;
       super.calculateCardDamage(mo);
       this.baseDamage = realBaseDamage;
       this.isDamageModified = (this.damage != this.baseDamage);
     }else{
       super.calculateCardDamage(mo);
     }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (abstractMonster!=null){
       addToBot(new VFXAction(new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.2F));
     }
     calculateCardDamage(abstractMonster);
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
     if (abstractPlayer.hasPower(NightVampirePower.POWER_ID)){
       addToBot(new SFXAction("NightVampirePower"));
       if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
         addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new PoisonPower(abstractMonster, abstractPlayer, 2)));
         addToBot(new HealAction(abstractPlayer,abstractPlayer,2));
       }else {
         addToBot(new LoseHPAction(abstractPlayer,abstractPlayer,1));
       }
     }
     if (abstractPlayer.hasPower(VanpiPower.POWER_ID)){
       addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new PoisonPower(abstractMonster, abstractPlayer, 2)));
       addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,2, DamageInfo.DamageType.HP_LOSS)));
       addToBot(new SFXAction("VanpiPower"));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ForestBat();
   }
 }

