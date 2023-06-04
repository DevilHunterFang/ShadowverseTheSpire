 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import shadowverse.action.NecromanceAction;
 import shadowverse.action.PrimalGigantAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Necromancer;


 public class DeadSoulTaker extends CustomCard {
   public static final String ID = "shadowverse:DeadSoulTaker";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeadSoulTaker");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DeadSoulTaker.png";

   public DeadSoulTaker() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 45;
       this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(3);
     } 
   }


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("DeadSoulTaker"));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.PURPLE, true),1.0f));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       addToBot((AbstractGameAction)new MoveCardsAction(abstractPlayer.hand,abstractPlayer.exhaustPile, card -> {
           return card.type==CardType.ATTACK && card.cardID!=this.cardID;
       },abstractCards -> {
           for (AbstractCard c:abstractCards){
               c.setCostForTurn(0);
               c.applyPowers();
           }
       }));
       addToBot((AbstractGameAction)new NecromanceAction(10,null,(AbstractGameAction)new PrimalGigantAction()));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new DeadSoulTaker();
   }
 }

