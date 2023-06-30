 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Curse.CurseOfTheBlackDragon;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;
import shadowverse.powers.RowenPower;


 public class RowenCard extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:RowenCard";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RowenCard");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RowenCard.png";

   public RowenCard() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY,2);
     this.baseDamage = 12;
     this.cardsToPreview = new CurseOfTheBlackDragon();
     ExhaustiveVariable.setBaseValue(this, 3);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }

   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("RowenCard_Eh"));
     addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
     addToBot(new ApplyPowerAction(p,p,new RowenPower(p,2,3)));
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("RowenCard"));
     addToBot(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new RowenCard();
   }
 }

