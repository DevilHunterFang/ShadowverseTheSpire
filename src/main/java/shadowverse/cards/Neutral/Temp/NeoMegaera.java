 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BlurPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;
 import shadowverse.powers.NeoMegaeraPower;


 public class NeoMegaera
   extends CustomCard {
   public static final String ID = "shadowverse:NeoMegaera";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NeoMegaera");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NeoMegaera.png";

   public NeoMegaera() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL);
     this.baseMagicNumber = 25;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(6);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("NeoMegaera"));
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new BlurPower(p,1),1));
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new NeoMegaeraPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NeoMegaera();
   }
 }

