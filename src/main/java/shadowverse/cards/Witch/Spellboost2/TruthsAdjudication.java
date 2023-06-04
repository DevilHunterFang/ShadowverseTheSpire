 package shadowverse.cards.Witch.Spellboost2;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


 public class TruthsAdjudication
   extends CustomCard
 {
   public static final String ID = "shadowverse:TruthsAdjudication";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TruthsAdjudication");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TruthsAdjudication.png";
   
   public TruthsAdjudication() {
     super("shadowverse:TruthsAdjudication", NAME, "img/cards/TruthsAdjudication.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 0;
     this.exhaust = true;
     this.selfRetain = true;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       this.magicNumber = ++this.baseMagicNumber;
     } 
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public void rand(int[] l, int n, int m)
   {
       int i;
       for(i=0;i<n-1;i++)
       {
           l[i] = AbstractDungeon.cardRandomRng.random(2 * m / (n - i));
           m -= l[i];
       }
       l[i] = m;
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     CardCrawlGame.sound.playA("TruthsAdjudication", 0.0F);
     int [] l = new int[3];
     rand(l,3,this.magicNumber);
     int x = l[0];
     int y = l[1];
     int z = l[2];
     
     if (this.upgraded) {
       this.baseDamage = y * 4;
       this.damage = this.baseDamage;
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 4));
       addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
       addToBot((AbstractGameAction)new HealAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, z * 2));
     } else {
       this.baseDamage = y * 3;
       this.damage = this.baseDamage;
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 3));
       addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
       addToBot((AbstractGameAction)new HealAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, z));
     } 
   }
 
   
   public void applyPowers() {
     super.applyPowers();
     int count = this.magicNumber;
     if (!this.upgraded)
     this.rawDescription = cardStrings.DESCRIPTION;
     else
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TruthsAdjudication();
   }
 }

