 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.Temp.AdherentOfScream_Acc;
import shadowverse.cards.Temp.Clarke_Accelerate;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class AdherentOfScream
   extends CustomCard
 {
   public static final String ID = "shadowverse:AdherentOfScream";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AdherentOfScream");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AdherentOfScream.png";

   public AdherentOfScream() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }

   @Override
   public void update() {
     if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
             Shadowverse.Accelerate(this)){
       setCostForTurn(0);
       this.type = CardType.SKILL;
     }else {
       if (this.type==CardType.SKILL){
         setCostForTurn(2);
         this.type = CardType.ATTACK;
       }
     }
     super.update();
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       addToBot((AbstractGameAction)new ReanimateAction(1));
     }else {
       addToBot((AbstractGameAction)new SFXAction("AdherentOfScream"));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       if (this.costForTurn>0){
         addToBot((AbstractGameAction)new ReanimateAction(1));
         addToBot((AbstractGameAction)new ReanimateAction(1));
       }
     }
   }

   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new AdherentOfScream_Acc()).makeStatEquivalentCopy();
       card.uuid = (new AdherentOfScream_Acc()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     }
     return card;
   }

   public AbstractCard makeCopy() {
     return (AbstractCard)new AdherentOfScream();
   }
 }

