 package shadowverse.cards.Necromancer.LastWords;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class FallenSergeant
   extends CustomCard {
   public static final String ID = "shadowverse:FallenSergeant";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FallenSergeant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FallenSergeant.png";

   public FallenSergeant() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 9;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
     this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }

   @Override
   public void triggerOnExhaust() {
     addToBot(new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.CONDEMNED,null));
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot(new GainBlockAction(p, this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FallenSergeant();
   }
 }

