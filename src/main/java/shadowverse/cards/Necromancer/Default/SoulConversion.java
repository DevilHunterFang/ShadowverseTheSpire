 package shadowverse.cards.Necromancer.Default;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.characters.Necromancer;


 public class SoulConversion extends CustomCard {
   public static final String ID = "shadowverse:SoulConversion";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SoulConversion");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SoulConversion.png";

   public SoulConversion() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }

     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         boolean canUse = super.canUse(p, m);
         if (!canUse)
             return false;
         boolean hasAttack = false;
         for (AbstractCard c : p.discardPile.group) {
             if (c.type == CardType.ATTACK)
                 hasAttack = true;
         }
         if (!hasAttack) {
             this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
             canUse = false;
         }
         return canUse;
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new DrawCardAction(this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SoulConversion();
   }
 }
