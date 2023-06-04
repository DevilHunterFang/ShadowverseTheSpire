 package shadowverse.cards.Witch.Spellboost2;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 
 public class FireChain
   extends CustomCard
 {
   public static final String ID = "shadowverse:FireChain";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FireChain");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FireChain.png";
   
   public FireChain() {
     super("shadowverse:FireChain", NAME, "img/cards/FireChain.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.baseDamage = 3;
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       
       this.magicNumber = ++this.baseMagicNumber;
       addToBot(new SFXAction("spell_boost"));
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     for (int i = 0; i < this.magicNumber; i++) {
       addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
             AbstractDungeon.actionManager.clearPostCombatActions();
     }
   }
 
   
   public void applyPowers() {
     super.applyPowers();
     int count = this.magicNumber;
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
 
   
   public AbstractCard makeCopy() {
     return new FireChain();
   }
 }

