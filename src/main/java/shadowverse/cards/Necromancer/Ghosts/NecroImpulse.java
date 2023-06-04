 package shadowverse.cards.Necromancer.Ghosts;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.characters.Necromancer;


 public class NecroImpulse
   extends CustomCard {
   public static final String ID = "shadowverse:NecroImpulse";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NecroImpulse");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NecroImpulse.png";

   public NecroImpulse() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new Ghost();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),4));
     addToBot((AbstractGameAction)new NecromanceAction(10,null,(AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,this.magicNumber),this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NecroImpulse();
   }
 }

