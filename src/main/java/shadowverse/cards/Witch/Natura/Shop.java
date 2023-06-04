 package shadowverse.cards.Witch.Natura;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.InvocationAction;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.ShopPower;
 
 
 
 public class Shop
   extends CustomCard
 {
   public static final String ID = "shadowverse:Shop";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Shop");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Shop.png";
   public int turnCount = 0;
   public static boolean dupCheck = true;
   public static boolean combatCheck = true;
   
   public Shop() {
     super("shadowverse:Shop", NAME, "img/cards/Shop.png", 3, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.isEthereal = true;
     combatCheck = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isEthereal = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
   
   public void atTurnStart() {
     this.turnCount++;
     if (this.turnCount >= 5 && dupCheck && combatCheck) {
         dupCheck = false;
         combatCheck = false;
       if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new DiscardToHandAction((AbstractCard)this));
         addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new VoidCard(), 2));
       } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new InvocationAction((AbstractCard)this));
         addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new VoidCard(), 2));
       }
     }else if (turnCount < 5){
       dupCheck = true;
     }
   }

   @Override
   public void triggerOnCardPlayed(AbstractCard cardPlayed) {
     dupCheck = true;
   }

   @Override
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     dupCheck = true;
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ShopPower((AbstractCreature)abstractPlayer, this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Shop();
   }
 }


