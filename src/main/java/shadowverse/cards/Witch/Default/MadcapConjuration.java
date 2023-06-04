 package shadowverse.cards.Witch.Default;
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.DiscardAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
 
 public class MadcapConjuration extends CustomCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MadcapConjuration"); public static final String ID = "shadowverse:MadcapConjuration";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MadcapConjuration.png";
   
   public MadcapConjuration() {
     super("shadowverse:MadcapConjuration", NAME, "img/cards/MadcapConjuration.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 6;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     int count = AbstractDungeon.player.hand.size();
     addToTop((AbstractGameAction)new DiscardAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, count, true));
     int attackCount = 0;
     int skillCount = 0;
     int powerCount = 0;
     for (AbstractCard c : abstractPlayer.hand.group) {
       if (c.type == CardType.ATTACK || c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)||c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)) {
         attackCount++; continue;
       }  if (c.type == CardType.SKILL && !c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE) && c!=this) {
         skillCount++; continue;
       }  if (c.type == CardType.POWER && !c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)) {
         powerCount++;
       }
     } 
     if (attackCount >= 2) {
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       } 
     }
     if (skillCount >= 2) {
       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 5));
     }
     if (powerCount >= 2) {
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.LIGHTNING, true));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MadcapConjuration();
   }
 }

