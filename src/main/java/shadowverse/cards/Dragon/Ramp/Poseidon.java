 package shadowverse.cards.Dragon.Ramp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Temp.PoseidonGuard;
import shadowverse.characters.Dragon;


 public class Poseidon extends CustomCard {
   public static final String ID = "shadowverse:Poseidon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Poseidon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Poseidon.png";

   public Poseidon() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 28;
     this.cardsToPreview = new PoseidonGuard();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
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
         setCostForTurn(4);
         this.type = CardType.ATTACK;
       }
     }
     super.update();
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL){
       addToBot(new SFXAction("Poseidon_Acc"));
       addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),1));
     }else {
       addToBot(new SFXAction("Poseidon"));
       addToBot(new GainBlockAction(abstractPlayer,this.block));
       addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Poseidon();
   }
 }

