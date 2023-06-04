 package shadowverse.cards.Neutral.Temp;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


 public class CyclicalGuidance extends AbstractEnhanceCard {
   public static final String ID = "shadowverse:CyclicalGuidance";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CyclicalGuidance");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CyclicalGuidance.png";

   public CyclicalGuidance() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ALL_ENEMY,5);
     this.baseDamage = 1;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   @Override
   public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

   }

   @Override
   public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("CyclicalGuidance_Eh"));
     int dmg = this.damage + 24;
     for (int i = 0; i < 4; i++){
       addToBot(new DamageRandomEnemyAction(new DamageInfo(p,dmg,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     }
   }

   @Override
   public void baseUse(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("CyclicalGuidance"));
     for (int i = 0; i < 4; i++){
       addToBot(new DamageRandomEnemyAction(new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     }
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new CyclicalGuidance();
   }
 }

