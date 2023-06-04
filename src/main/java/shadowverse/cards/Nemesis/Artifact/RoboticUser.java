 package shadowverse.cards.Nemesis.Artifact;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ParadigmShift;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class RoboticUser
   extends CustomCard {
   public static final String ID = "shadowverse:RoboticUser";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RoboticUser");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RoboticUser.png";

   public RoboticUser() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 4;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new ParadigmShift();
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("RoboticUser"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RoboticUser();
   }
 }

