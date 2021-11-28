 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.ReduceCountDownAction;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;


 public class UnlikelyFellowship
   extends CustomCard {
   public static final String ID = "shadowverse:UnlikelyFellowship";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnlikelyFellowship");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/UnlikelyFellowship.png";

   public UnlikelyFellowship() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot((AbstractGameAction)new SFXAction("UnlikelyFellowship"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)m, -this.magicNumber), -this.magicNumber));
     if (m != null && !m.hasPower("Artifact"))
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new GainStrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
     addToBot((AbstractGameAction)new ReduceCountDownAction(this.magicNumber));
   }

   
   public AbstractCard makeCopy() {
     return (AbstractCard)new UnlikelyFellowship();
   }
 }

