 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Temp.MysticArtifact;
import shadowverse.characters.Nemesis;


 public class IronStaffMechanic
   extends CustomCard {
   public static final String ID = "shadowverse:IronStaffMechanic";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IronStaffMechanic");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/IronStaffMechanic.png";

   public IronStaffMechanic() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 5;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new MysticArtifact();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("IronStaffMechanic"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), this.magicNumber, true, true));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new IronStaffMechanic();
   }
 }

