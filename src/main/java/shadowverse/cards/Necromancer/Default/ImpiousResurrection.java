 package shadowverse.cards.Necromancer.Default;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.cards.Neutral.Temp.Litch;
import shadowverse.characters.Necromancer;


 public class ImpiousResurrection extends CustomCard {
   public static final String ID = "shadowverse:ImpiousResurrection";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImpiousResurrection");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImpiousResurrection.png";

   public ImpiousResurrection() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.cardsToPreview = (AbstractCard)new Litch();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
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
     addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ImpiousResurrection();
   }
 }
