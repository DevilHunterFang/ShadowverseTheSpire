 package shadowverse.cards.Bishop.Amulet1;

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
 import shadowverse.cards.AbstractAmuletCard;
 import shadowverse.cards.AbstractCrystalizeCard;
 import shadowverse.cards.AbstractNoCountDownAmulet;
 import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Bishop;

 public class Agnes
   extends CustomCard {
   public static final String ID = "shadowverse:Agnes";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Agnes");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Agnes.png";
   public static final int BASE_COST = 6;

   public Agnes() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 28;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c instanceof AbstractAmuletCard || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER) || c instanceof AbstractNoCountDownAmulet) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     }
     if (c instanceof NaterranGreatTree){
         flash();
         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
         addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     }
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(8);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Agnes"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     this.cost = BASE_COST;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Agnes();
   }
 }

