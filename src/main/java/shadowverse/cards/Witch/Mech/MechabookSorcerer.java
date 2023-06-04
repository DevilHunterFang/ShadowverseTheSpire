 package shadowverse.cards.Witch.Mech;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.MechabookSorcererPower;
 
 public class MechabookSorcerer
   extends CustomCard
 {
   public static final String ID = "shadowverse:MechabookSorcerer";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MechabookSorcerer");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MechabookSorcerer.png";
   
   public MechabookSorcerer() {
     super("shadowverse:MechabookSorcerer", NAME, "img/cards/MechabookSorcerer.png", 3, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isEthereal = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:MechabookSorcererPower")) {
         powerExists = true;
         break;
       } 
     } 
     if (!powerExists) {
       addToBot((AbstractGameAction)new SFXAction("MechabookSorcerer"));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new MechabookSorcererPower((AbstractCreature)abstractPlayer)));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MechabookSorcerer();
   }
 }

