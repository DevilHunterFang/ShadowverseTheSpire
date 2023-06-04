 package shadowverse.cards.Elf.NatMech;


 import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


 public class RoboticBagworm extends CustomCard {
   public static final String ID = "shadowverse:RoboticBagworm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RoboticBagworm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RoboticBagworm.png";
   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ChooseToReduceCost")).TEXT;

   public RoboticBagworm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 2;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,true, card -> {
       return card.hasTag(AbstractShadowversePlayer.Enums.MACHINE);
     }, abstractCards ->{
       for (AbstractCard c:abstractCards){
         c.flash();
         addToBot((AbstractGameAction)new ReduceCostForTurnAction(c,1));
       }
     } ));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RoboticBagworm();
   }
 }
