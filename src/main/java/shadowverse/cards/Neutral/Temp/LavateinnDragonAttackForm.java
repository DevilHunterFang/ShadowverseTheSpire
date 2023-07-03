 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.LavateinnDragonAttackFormPower;


 public class LavateinnDragonAttackForm extends CustomCard {
   public static final String ID = "shadowverse:LavateinnDragonAttackForm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LavateinnDragonAttackForm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LavateinnDragonAttackForm.png";

   public LavateinnDragonAttackForm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
     this.baseDamage = 25;
     this.isMultiDamage = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LavateinnDragonAttackFormPower(abstractPlayer,1)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LavateinnDragonAttackForm();
   }
 }

