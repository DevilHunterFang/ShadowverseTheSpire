 package shadowverse.cards.Royal.Default;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Royal;


 public class FighterFortitude
   extends CustomCard
 {
   public static final String ID = "shadowverse:FighterFortitude";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FighterFortitude");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FighterFortitude.png";

   public FighterFortitude() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.HERO);
   }

   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(-1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new SFXAction("FighterFortitude"));
       addToBot(new LoseHPAction(p,p,this.magicNumber));
       addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FighterFortitude();
   }
 }

