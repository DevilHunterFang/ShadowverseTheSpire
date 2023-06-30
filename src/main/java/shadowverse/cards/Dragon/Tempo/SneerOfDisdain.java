 package shadowverse.cards.Dragon.Tempo;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


 public class SneerOfDisdain extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:SneerOfDisdain";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SneerOfDisdain");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SneerOfDisdain.png";

   public SneerOfDisdain() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY,2);
     this.baseDamage = 9;
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new Burn();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeMagicNumber(1);
     } 
   }


   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("SneerOfDisdain"));
     addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
     for (AbstractCard c : p.hand.group) {
       if (c instanceof Burn)
         cardsToExhaust.add(c);
     }
     for (AbstractCard c : cardsToExhaust){
       addToBot(new ExhaustSpecificCardAction(c,p.hand));
       addToBot(new GainBlockAction(p,this.magicNumber));
     }
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("SneerOfDisdain"));
     addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new SneerOfDisdain();
   }
 }

