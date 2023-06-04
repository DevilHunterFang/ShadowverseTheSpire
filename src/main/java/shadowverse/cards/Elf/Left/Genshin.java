 package shadowverse.cards.Elf.Left;


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
import shadowverse.characters.Elf;
import shadowverse.powers.GenshinPower;


 public class Genshin extends CustomCard {
   public static final String ID = "shadowverse:Genshin";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Genshin");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Genshin.png";

   public Genshin() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
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
       if (!abstractPlayer.hasPower("shadowverse:GenshinPower"))
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new GenshinPower((AbstractCreature)abstractPlayer)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Genshin();
   }
 }

