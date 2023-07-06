 package shadowverse.cards.Witch.Earth2;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.GainStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;

 public class BindingRitual extends CustomCard {
   public static final String ID = "shadowverse:BindingRitual";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BindingRitual");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BindingRitual.png";
   
   public BindingRitual() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,new EarthEssence(abstractPlayer, this.magicNumber), this.magicNumber));
     addToBot(new DrawCardAction(1));
     AbstractMonster m = AbstractDungeon.getRandomMonster();
     addToBot(new ApplyPowerAction(m, abstractPlayer, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
     addToBot(new ApplyPowerAction(m,abstractPlayer, new GainStrengthPower(m,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BindingRitual();
   }
 }

