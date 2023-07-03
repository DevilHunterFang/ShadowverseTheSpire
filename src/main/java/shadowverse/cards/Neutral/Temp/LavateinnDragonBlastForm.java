 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class LavateinnDragonBlastForm extends CustomCard {
   public static final String ID = "shadowverse:LavateinnDragonBlastForm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LavateinnDragonBlastForm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LavateinnDragonBlastForm.png";

   public LavateinnDragonBlastForm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 0;
     this.baseMagicNumber = 7;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }

   public void applyPowers() {
     int amount = 0;
     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.hasTag(AbstractShadowversePlayer.Enums.ARMED) && c.type == CardType.ATTACK){
         amount++;
       }
     }
     int realBaseDamage = this.baseDamage;
     this.baseDamage += amount * this.magicNumber;
     super.applyPowers();
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }

   public void calculateCardDamage(AbstractMonster mo) {
     int amount = -1;
     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.hasTag(AbstractShadowversePlayer.Enums.ARMED) && c.type == CardType.ATTACK){
         amount++;
       }
     }
     int realBaseDamage = this.baseDamage;
     this.baseDamage += amount * this.magicNumber;
     super.calculateCardDamage(mo);
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("ATTACK_HEAVY"));
     addToBot(new GainEnergyAction(1));
     calculateCardDamage(abstractMonster);
     addToBot(new VFXAction(abstractPlayer, new MindblastEffect(abstractPlayer.dialogX, abstractPlayer.dialogY, abstractPlayer.flipHorizontal), 0.1F));
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LavateinnDragonBlastForm();
   }
 }

