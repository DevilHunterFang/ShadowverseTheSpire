 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import shadowverseCharbosses.powers.cardpowers.EnemyFlameBarrierPower;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;


 public class LionBless
   extends CustomCard
 {
   public static final String ID = "shadowverse:LionBless";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LionBless");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LionBless.png";

   public LionBless() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.exhaust = true;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, 24));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyFlameBarrierPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
       addToBot((AbstractGameAction)new MoveCardsAction(abstractPlayer.hand,abstractPlayer.exhaustPile, card -> {
           return card.type==CardType.ATTACK;
       },abstractCards -> {
           for (AbstractCard c:abstractCards){
               c.setCostForTurn(0);
               c.applyPowers();
           }
       }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LionBless();
   }
 }

