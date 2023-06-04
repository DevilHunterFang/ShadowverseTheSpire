 package shadowverse.cards.Witch.Spellboost1;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
 
 public class FatesHand
   extends CustomCard
 {
   public static final String ID = "shadowverse:FatesHand";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FatesHand");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FatesHand.png";
   public static final int BASE_COST = 3;
   
   public FatesHand() {
     super("shadowverse:FatesHand", NAME, "img/cards/FatesHand.png", 3, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.NONE);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
     } 
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, this.magicNumber));
     this.cost = 3;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FatesHand();
   }
 }


