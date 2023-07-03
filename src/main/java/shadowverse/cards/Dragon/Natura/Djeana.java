 package shadowverse.cards.Dragon.Natura;
 


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class Djeana extends AbstractRightClickCard2 {
   public static final String ID = "shadowverse:Djeana";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Djeana");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Djeana.png";
   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
   public static final String[] TEXT = uiStrings.TEXT;
   private boolean hasFusion = false;


   public Djeana() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 8;
     this.cardsToPreview = new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Djeana"));
     addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
   }

   public void atTurnStart(){
     hasFusion = false;
   }

   public AbstractCard makeCopy() {
     return (AbstractCard)new Djeana();
   }

   @Override
   protected void onRightClick() {
     if (!this.hasFusion && AbstractDungeon.player!=null){
       addToBot(new SelectCardsInHandAction(9,TEXT[0],true,true, card -> {
         return card instanceof NaterranGreatTree;
       }, abstractCards -> {
         for (AbstractCard c:abstractCards){
           addToBot(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
         }
         if (abstractCards.size()>0){
           addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new OverflowPower(AbstractDungeon.player,1),1));
         }
       }));
       this.hasFusion = true;
     }
   }
 }

