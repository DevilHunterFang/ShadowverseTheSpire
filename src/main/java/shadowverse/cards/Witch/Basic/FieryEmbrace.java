 package shadowverse.cards.Witch.Basic;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
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
 
 public class FieryEmbrace
   extends CustomCard {
   public static final String ID = "shadowverse:FieryEmbrace";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FieryEmbrace");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FieryEmbrace.png";
   public static final int BASE_COST = 4;
   
   public FieryEmbrace() {
     super("shadowverse:FieryEmbrace", NAME, "img/cards/FieryEmbrace.png", BASE_COST, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.BASIC, CardTarget.ENEMY);
     this.baseDamage = 14;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
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
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, this.damage, AbstractGameAction.AttackEffect.FIRE));
     this.cost = BASE_COST;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FieryEmbrace();
   }
 }

