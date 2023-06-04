 package shadowverse.cards.Nemesis.Puppet;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Temp.Puppet;
 import shadowverse.characters.Nemesis;
 import shadowverse.powers.LichtPower;


 public class Licht extends CustomCard {
   public static final String ID = "shadowverse:Licht";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Licht");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Licht.png";

   public Licht() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new Puppet();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       addToBot((AbstractGameAction)new SFXAction("Licht"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new LichtPower((AbstractCreature)abstractPlayer,this.magicNumber),this.magicNumber));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Licht();
   }
 }

