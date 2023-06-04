 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.ProphecyOfDoomPower;

 public class ProphecyOfDoom
   extends CustomCard {
   public static final String ID = "shadowverse:ProphecyOfDoom";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ProphecyOfDoom");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ProphecyOfDoom.png";

   public ProphecyOfDoom() {
     super(ID, NAME, IMG_PATH, 9, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 49;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
     this.selfRetain = true;
     this.exhaust = true;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     } 
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(7);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ProphecyOfDoom"));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,(AbstractPower)new ProphecyOfDoomPower(abstractMonster,this.magicNumber),this.magicNumber));
     this.cost = 9;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ProphecyOfDoom();
   }
 }

