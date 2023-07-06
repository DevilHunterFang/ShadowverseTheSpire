 package shadowverse.cards.Witch.Earth1;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;


 public class AcidGolem extends CustomCard {
   public static final String ID = "shadowverse:AcidGolem";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AcidGolem");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AcidGolem.png";

   public AcidGolem() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 12;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("AcidGolem"));
     AbstractPower p = abstractPlayer.getPower(EarthEssence.POWER_ID);
     if (p!=null){
         if (p.amount>0){
             if (abstractPlayer instanceof  AbstractShadowversePlayer){
                 ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
             }
             addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, -1), -1));
             addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
         }
         if (p.amount>1){
             if (abstractPlayer instanceof  AbstractShadowversePlayer){
                 ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
             }
             addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, -1), -1));
             addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
         }
         if (p.amount>2){
             if (abstractPlayer instanceof  AbstractShadowversePlayer){
                 ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
             }
             addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EarthEssence(abstractPlayer, -1), -1));
             addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
         }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new AcidGolem();
   }
 }


