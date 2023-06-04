 package shadowverse.cards.Elf.Default;


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
 import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
 import shadowverse.characters.Elf;


 public class Lycoris extends CustomCard {
   public static final String ID = "shadowverse:Lycoris";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lycoris");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lycoris.png";

   public Lycoris() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Lycoris"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new NoxiousFumesPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lycoris();
   }
 }

