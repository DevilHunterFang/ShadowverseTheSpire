 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.characters.Dragon;


 public class CrimsonDragon extends CustomCard {
   public static final String ID = "shadowverse:CrimsonDragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CrimsonDragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CrimsonDragon.png";

   public CrimsonDragon() {
     super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 24;
     this.exhaust = true;
     this.selfRetain = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }

   public void atTurnStart() {
     resetAttributes();
     applyPowers();
   }

   public void triggerWhenDrawn() {
     super.triggerWhenDrawn();
     setCostForTurn(this.cost - GameActionManager.totalDiscardedThisTurn);
   }

   public void didDiscard() {
     setCostForTurn(this.costForTurn - 1);
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
     addToBot(new DrawCardAction(2));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
   }

   public AbstractCard makeCopy() {
     AbstractCard tmp = new CrimsonDragon();
     if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
             (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
       setCostForTurn(this.cost - GameActionManager.totalDiscardedThisTurn);
     return tmp;
   }

 }

