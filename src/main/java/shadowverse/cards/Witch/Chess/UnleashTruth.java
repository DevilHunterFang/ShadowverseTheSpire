 package shadowverse.cards.Witch.Chess;

 import basemod.abstracts.CustomCard;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Witch.Spellboost1.OmenOfTruth;
 import shadowverse.cards.Neutral.Temp.PerjuryOfTruth;
 import shadowverse.characters.Witchcraft;

 public class UnleashTruth extends CustomCard {
   public static final String ID = "shadowverse:UnleashTruth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnleashTruth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/UnleashTruth.png";

   public UnleashTruth() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isInnate = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("UnleashTruth"));
       addToBot((AbstractGameAction)new GainEnergyAction(1));
       addToBot((AbstractGameAction)new MoveCardsAction(abstractPlayer.hand,abstractPlayer.drawPile,card ->
               card.color == Witchcraft.Enums.COLOR_BLUE,abstractCards -> {
           for (AbstractCard c:abstractCards){
               if (c.cost>=3){
                   addToBot((AbstractGameAction)new ReduceCostForTurnAction(c,1));
               }
               if (c instanceof OmenOfTruth && ((OmenOfTruth) c).chosenBranch()==1){
                   addToBot((AbstractGameAction)new MakeTempCardInHandAction(new PerjuryOfTruth()));
               }
           }
       }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new UnleashTruth();
   }
 }

