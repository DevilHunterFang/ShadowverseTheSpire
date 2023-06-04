 package shadowverse.cards.Witch.Mech;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

 
 public class GrimoireSorcerer
   extends CustomCard {
   public static final String ID = "shadowverse:GrimoireSorcerer";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GrimoireSorcerer");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GrimoireSorcerer.png";
   public static final int BASE_COST = 6;
   
   public GrimoireSorcerer() {
     super(ID, NAME,IMG_PATH, 6, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 16;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     } 
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("GrimoireSorcerer"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
       for (AbstractCard c : abstractPlayer.hand.group) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)&&c!=this) {
               c.flash();
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
               addToBot((AbstractGameAction)new ReduceCostAction(c));
               addToBot((AbstractGameAction)new ReduceCostAction(c));
           }
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)&&c!=this) {
               c.flash();
               c.baseMagicNumber += 2;
               c.magicNumber = c.baseMagicNumber;
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
           }
       }
       this.cost = 6;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GrimoireSorcerer();
   }
 }

