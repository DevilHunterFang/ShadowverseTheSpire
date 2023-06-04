 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

 
 
 
 public class ProphecyOfBoons
   extends CustomCard
 {
   public static final String ID = "shadowverse:ProphecyOfBoons";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ProphecyOfBoons");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ProphecyOfBoons.png";
   
   public ProphecyOfBoons() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.selfRetain = true;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       this.magicNumber = ++this.baseMagicNumber;
     } 
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ProphecyOfBoons"));
     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 3));
     if (this.magicNumber >= 3) {
       addToBot((AbstractGameAction)new GainEnergyAction(1));
     }
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
   }
   
   public void applyPowers() {
     super.applyPowers();
     int count = this.magicNumber;
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ProphecyOfBoons();
   }
 }

