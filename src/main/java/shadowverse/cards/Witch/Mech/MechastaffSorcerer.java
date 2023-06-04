 package shadowverse.cards.Witch.Mech;
 
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
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 public class MechastaffSorcerer
   extends CustomCard
 {
   public static final String ID = "shadowverse:MechastaffSorcerer";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MechastaffSorcerer");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MechastaffSorcerer.png";
   
   public MechastaffSorcerer() {
     super("shadowverse:MechastaffSorcerer", NAME, "img/cards/MechastaffSorcerer.png", 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 16;
     this.baseBlock = 12;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeDamage(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("MechastaffSorcerer"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     int machineCount = 0;
     for (AbstractCard c : abstractPlayer.hand.group) {
       if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this) {
         machineCount++;
       }
     } 
     if (machineCount >= 1) {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MechastaffSorcerer();
   }
 }

