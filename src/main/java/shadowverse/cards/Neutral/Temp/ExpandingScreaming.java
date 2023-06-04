 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Necromancer.Default.OmenOfSilence;
import shadowverse.characters.Necromancer;


 public class ExpandingScreaming extends CustomCard {
   public static final String ID = "shadowverse:ExpandingScreaming";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ExpandingScreaming");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ExpandingScreaming.png";

   public ExpandingScreaming() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
     this.exhaust = true;
     this.cardsToPreview = new OmenOfSilence2Copy();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public void applyPowers() {
     super.applyPowers();
     int count = 0;
     for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.cardID.contains("OmenOfSilence")){
         count++;
       }
     }
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ExpandingScreaming"));
     AbstractCard omenOfSilence = null;
     for (AbstractCard card: abstractPlayer.exhaustPile.group){
       if (card instanceof OmenOfSilence && ((OmenOfSilence) card).chosenBranch()==1)
         omenOfSilence = card;
     }
     if (null!=omenOfSilence){
       omenOfSilence.cost = 1;
       omenOfSilence.costForTurn = 1;
       omenOfSilence.isCostModified = false;
       addToBot((AbstractGameAction) new NecromanceAction(3, null, (AbstractGameAction) new MakeTempCardInHandAction(omenOfSilence.makeStatEquivalentCopy())));
     }
     int count = 0;
     for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
       if (c.cardID.contains("OmenOfSilence")){
         count++;
       }
     }
     if (count > 11-abstractPlayer.hand.group.size()){
       count = 11-abstractPlayer.hand.group.size();
     }
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(new OmenOfSilence2Copy(),count));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ExpandingScreaming();
   }
 }

