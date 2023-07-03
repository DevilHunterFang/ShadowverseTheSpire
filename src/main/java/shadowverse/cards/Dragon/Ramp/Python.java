 package shadowverse.cards.Dragon.Ramp;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.characters.Dragon;


 public class Python extends CustomCard {
   public static final String ID = "shadowverse:Python";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Python");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Python.png";
   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
   public static final String[] TEXT = uiStrings.TEXT;

   public Python() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 27;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(7);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
     AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
     addToBot(new SelectCardsAction(abstractPlayer.drawPile.group,10,TEXT[0],true,card -> {
       return true;
     },abstractCards -> {
       for(AbstractCard c:abstractCards){
         addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
       }
     }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Python();
   }
 }

