 package shadowverse.cards.Elf.Wood;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;
import shadowverse.powers.GreenbrierElfPower;


 public class GreenbrierElf extends CustomCard {
   public static final String ID = "shadowverse:GreenbrierElf";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GreenbrierElf");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GreenbrierElf.png";

   public GreenbrierElf() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new GreenWoodGuardian();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("GreenbrierElf"));
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot(new MakeTempCardInHandAction(c,1));
     addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new GreenbrierElfPower(abstractPlayer,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return new GreenbrierElf();
   }
 }

