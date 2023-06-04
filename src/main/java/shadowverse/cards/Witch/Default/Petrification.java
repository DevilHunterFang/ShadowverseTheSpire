 package shadowverse.cards.Witch.Default;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;


 public class Petrification
   extends CustomCard
 {
   public static final String ID = "shadowverse:Petrification";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Petrification");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Petrification.png";
   
   public Petrification() {
     super("shadowverse:Petrification", NAME, "img/cards/Petrification.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:EarthEssence")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
         if (abstractPlayer instanceof  AbstractShadowversePlayer){
             ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
         }
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)abstractPlayer, 1), 1));
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
     } else {
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)abstractMonster, -this.magicNumber), -this.magicNumber));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Petrification();
   }
 }

