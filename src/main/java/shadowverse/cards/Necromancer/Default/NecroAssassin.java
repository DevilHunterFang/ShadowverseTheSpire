 package shadowverse.cards.Necromancer.Default;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.characters.Necromancer;


 public class NecroAssassin extends CustomCard {
   public static final String ID = "shadowverse:NecroAssassin";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NecroAssassin");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NecroAssassin.png";

   public NecroAssassin() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
     } 
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("NecroAssassin"));
     addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new GainEnergyAction(this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NecroAssassin();
   }
 }
