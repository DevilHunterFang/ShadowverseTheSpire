 package shadowverse.cards.Dragon.Ramp;
 


import basemod.abstracts.CustomCard;
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
import shadowverse.Shadowverse;
import shadowverse.characters.Dragon;


 public class PumpkinDragon extends CustomCard {
   public static final String ID = "shadowverse:PumpkinDragon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PumpkinDragon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PumpkinDragon.png";

   public PumpkinDragon() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 20;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
       upgradeMagicNumber(1);
     } 
   }

   @Override
   public void update() {
     if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
             Shadowverse.Accelerate(this)) {
       setCostForTurn(1);
       this.type = CardType.SKILL;
     } else {
       if (this.type == CardType.SKILL) {
         setCostForTurn(3);
         this.type = CardType.ATTACK;
       }
     }
     super.update();
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL){
       addToBot(new DrawCardAction(this.magicNumber));
     }else {
       addToBot(new GainBlockAction(abstractPlayer,this.block));
       addToBot(new DrawCardAction(this.magicNumber));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PumpkinDragon();
   }
 }

