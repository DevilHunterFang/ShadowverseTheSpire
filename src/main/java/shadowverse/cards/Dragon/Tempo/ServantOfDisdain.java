 package shadowverse.cards.Dragon.Tempo;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


 public class ServantOfDisdain extends CustomCard {
   public static final String ID = "shadowverse:ServantOfDisdain";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ServantOfDisdain");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ServantOfDisdain.png";

   public ServantOfDisdain() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("ServantOfDisdain"));
     addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
     for (AbstractCard c : abstractPlayer.hand.group) {
       if (c instanceof Burn)
         cardsToExhaust.add(c);
     }
     for (AbstractCard c : cardsToExhaust){
       addToBot(new ExhaustSpecificCardAction(c,abstractPlayer.hand));
       addToBot(new DrawCardAction(1));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ServantOfDisdain();
   }
 }

