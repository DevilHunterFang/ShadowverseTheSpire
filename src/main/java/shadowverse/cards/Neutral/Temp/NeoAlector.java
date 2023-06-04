 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;
 import shadowverse.powers.NeoAlectorPower;


 public class NeoAlector
   extends CustomCard {
   public static final String ID = "shadowverse:NeoAlector";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NeoAlector");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NeoAlector.png";

   public NeoAlector() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 14;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("NeoAlector"));
     addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new MetallicizePower(p,this.magicNumber),this.magicNumber));
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new NeoAlectorPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NeoAlector();
   }
 }

