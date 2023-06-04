 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Witchcraft;


 public class ShikigamiSummoning
   extends CustomCard {
   public static final String ID = "shadowverse:ShikigamiSummoning";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShikigamiSummoning");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ShikigamiSummoning.png";

   public ShikigamiSummoning() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.NONE);
     this.selfRetain = true;
   }

   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractCard c = null;
       int drawPileAmt = abstractPlayer.drawPile.group.size();
       int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
       if (drawPileAmt <= masterDeckAmt/2){
           c = new DemonicShikigami();
       }else {
           c = new PaperShikigami();
       }
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShikigamiSummoning();
   }
 }

