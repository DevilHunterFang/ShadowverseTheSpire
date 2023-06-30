 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class Drazael extends CustomCard {
   public static final String ID = "shadowverse:Drazael";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Drazael");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Drazael.png";

   public Drazael() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 24;
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(8);
     } 
   }

   @Override
   public void update() {
     if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
             Shadowverse.Accelerate(this)) {
       setCostForTurn(0);
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
       addToBot(new SFXAction("Drazael_Acc"));
       addToBot(new DrawCardAction(1));
     }else {
       addToBot(new SFXAction("Drazael"));
       addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
       addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ArtifactPower(abstractPlayer,1),1));
       addToBot(new HealAction(abstractPlayer,abstractPlayer,5));
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
         if (mo!=null && !mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead){
           addToBot(new ApplyPowerAction(mo, abstractPlayer, new StrengthPower(mo, -5), -5, true, AbstractGameAction.AttackEffect.NONE));
           addToBot(new ApplyPowerAction(mo, abstractPlayer, new DexterityPower(mo, -5), -5, true, AbstractGameAction.AttackEffect.NONE));
         }
       }
       this.exhaust = true;
       abstractPlayer.hand.removeCard(this);
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Drazael();
   }
 }

