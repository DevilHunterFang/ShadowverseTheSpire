 package shadowverse.cards.Neutral.Neutral;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
 import shadowverse.cards.AbstractNeutralCard;
 import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
 
 public class DualAngle
   extends AbstractNeutralCard {
   public static final String ID = "shadowverse:DualAngle";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DualAngle");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DualAngle.png";
   
   public DualAngle() {
     super("shadowverse:DualAngle", NAME, "img/cards/DualAngle.png", 1, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 10;
     this.baseBlock = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeDamage(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("DualAngle"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     int machineCount = 0;
     int naturalCount = 0;
     for (AbstractCard c : abstractPlayer.hand.group) {
       if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this) {
         machineCount++; 
       }
     }
       for (AbstractCard c : abstractPlayer.hand.group) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.NATURAL)&&c!=this) {
               naturalCount++;
           }
       }
       if (machineCount >= 2) {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     }
     if (naturalCount >= 2) {
       addToBot((AbstractGameAction)new HealAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, 2));
     }
     addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[] { (AbstractCard)new ProductMachine(), (AbstractCard)new NaterranGreatTree() }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DualAngle();
   }
 }

