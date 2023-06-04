 package shadowverse.cards.Witch.Spellboost2;
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.action.GhiosAction;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
 
 public class Ghios extends CustomCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ghios"); public static final String ID = "shadowverse:Ghios";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ghios.png";
   
   public Ghios() {
     super("shadowverse:Ghios", NAME, "img/cards/Ghios.png", 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 13;
     this.baseBlock = 13;
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
       upgradeBlock(5);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       this.magicNumber = ++this.baseMagicNumber;
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
     } 
   }
 
   
   public void onRetained() {
       addToBot((AbstractGameAction)new GhiosAction(this));
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Ghios"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
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
     return (AbstractCard)new Ghios();
   }
 }

