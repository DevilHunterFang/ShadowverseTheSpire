 package shadowverse.cards.Necromancer.Burial;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.action.BurialAction;
 import shadowverse.characters.Necromancer;
 import shadowverse.powers.MementoPower;


 public class DemonicProcession extends CustomCard {
   public static final String ID = "shadowverse:DemonicProcession";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DemonicProcession");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DemonicProcession.png";

   public DemonicProcession() {
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
         for (AbstractCard c : p.hand.group) {
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
       addToBot(new BurialAction(1,new DrawPileToHandAction(this.magicNumber, AbstractCard.CardType.ATTACK)));
       int attackAmt = 0;
       for (AbstractCard c : abstractPlayer.hand.group) {
           if (c != this && c.type == CardType.ATTACK)
               attackAmt++;
       }
       if (attackAmt >= 1) {
           if (abstractPlayer.hasPower(MementoPower.POWER_ID)) {
               addToBot(new DrawPileToHandAction(this.magicNumber, AbstractCard.CardType.ATTACK));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DemonicProcession();
   }
 }
