 package shadowverse.cards.Nemesis.Mech;


 import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class LasershellTortoise extends CustomCard {
   public static final String ID = "shadowverse:LasershellTortoise";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LasershellTortoise");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LasershellTortoise.png";
   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ChooseToReduceCost")).TEXT;

   public LasershellTortoise() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 10;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false, card -> {
       return card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)||card.hasTag(AbstractShadowversePlayer.Enums.NATURAL);
     }, abstractCards ->{
       for (AbstractCard c:abstractCards){
         c.flash();
         c.setCostForTurn(0);
       }
     } ));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LasershellTortoise();
   }
 }
