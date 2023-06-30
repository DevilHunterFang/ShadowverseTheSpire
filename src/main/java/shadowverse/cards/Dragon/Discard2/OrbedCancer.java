 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.Shadowverse;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class OrbedCancer extends CustomCard {
   public static final String ID = "shadowverse:OrbedCancer";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OrbedCancer");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OrbedCancer.png";

   public OrbedCancer() {
     super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 24;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
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
         setCostForTurn(5);
         this.type = CardType.ATTACK;
       }
     }
     super.update();
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL){
       addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
       addToBot(new DrawCardAction(this.magicNumber));
     }else {
       addToBot(new GainBlockAction(abstractPlayer,this.block));
       addToBot(new GainBlockAction(abstractPlayer,this.block));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OrbedCancer();
   }
 }

