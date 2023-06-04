 package shadowverse.cards.Bishop.MechLion;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import shadowverse.cards.Neutral.Temp.LionCrystalCopy;
import shadowverse.characters.Bishop;


 public class SalvationLion
   extends CustomCard {
   public static final String ID = "shadowverse:SalvationLion";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SalvationLion");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SalvationLion.png";

   public SalvationLion() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 8;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new LionCrystal();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new PlatedArmorPower(abstractPlayer,this.magicNumber),this.magicNumber));
     AbstractCard c = new LionCrystalCopy();
     c.setCostForTurn(0);
     addToBot((AbstractGameAction) new MakeTempCardInHandAction(c));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SalvationLion();
   }
 }

