 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.cards.Dragon.Tempo.IvoryDragon;
import shadowverse.characters.Dragon;


 public class Heliodragon extends CustomCard {
   public static final String ID = "shadowverse:Heliodragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Heliodragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Heliodragon.png";
   private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];
   
   public Heliodragon() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 5;
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new IvoryDragon();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeMagicNumber(1);
     } 
   }

   @Override
   public void triggerOnManualDiscard() {
     AbstractCard tmp = this.cardsToPreview.makeStatEquivalentCopy();
     tmp.exhaustOnUseOnce = true;
     tmp.exhaust = true;
     tmp.rawDescription += " NL " + TEXT + " 。";
     tmp.initializeDescription();
     tmp.applyPowers();
     AbstractDungeon.player.hand.addToTop(tmp);
   }

   public void triggerWhenDrawn() {
     super.triggerWhenDrawn();
     this.baseBlock += this.magicNumber * GameActionManager.totalDiscardedThisTurn;
     this.isBlockModified = true;
   }

   public void atTurnStart() {
     this.baseBlock = upgraded?7:5;
     resetAttributes();
     applyPowers();
   }

   public void didDiscard() {
     this.baseBlock += this.magicNumber;
     applyPowers();
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,this.block));}

   public AbstractCard makeCopy() {
     AbstractCard tmp = new Heliodragon();
     if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
             (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
       tmp.block = this.block;
     return tmp;
   }

 }

