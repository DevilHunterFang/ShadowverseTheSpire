 package shadowverse.cards.Vampire.Default;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Vampire;
 import shadowverse.powers.RaveningPower;


 public class Ravening
   extends CustomCard
 {
   public static final String ID = "shadowverse:Ravening";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ravening");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ravening.png";

   public Ravening() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isInnate = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new RaveningPower(p,this.upgraded)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ravening();
   }
 }

