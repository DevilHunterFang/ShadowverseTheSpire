 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;
import shadowverse.powers.WhiteArtifactPower;


 public class WhiteArtifact
   extends CustomCard
 {
   public static final String ID = "shadowverse:WhiteArtifact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WhiteArtifact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WhiteArtifact.png";

   public WhiteArtifact() {
     super(ID, NAME, IMG_PATH, 10, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseMagicNumber = 10;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new BlackArtifact();
     this.selfRetain = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(5);
     } 
   }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (c.exhaust && c.type==CardType.ATTACK) {
             flash();
             addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
         }
     }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("WhiteArtifact"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new WhiteArtifactPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WhiteArtifact();
   }
 }
