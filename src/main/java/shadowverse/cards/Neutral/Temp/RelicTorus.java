 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;


 public class RelicTorus
   extends CustomCard implements AbstractNoCountDownAmulet {
   public static final String ID = "shadowverse:RelicTorus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RelicTorus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RelicTorus.png";

   public RelicTorus() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RelicTorus();
   }

   @Override
   public void onStartOfTurn(AmuletOrb paramOrb) {
       boolean hasGod = false;
       for (AbstractOrb o :AbstractDungeon.player.orbs){
         if (o instanceof AmuletOrb){
           if (((AmuletOrb) o).amulet instanceof RelicGod){
             hasGod = true;
             break;
           }
         }
       }
       if (hasGod){
         addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(this.magicNumber*3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
       }else {
         addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
       }
   }

   @Override
   public void onEvoke(AmuletOrb paramOrb) {

   }

   @Override
   public void endOfTurn(AmuletOrb paramOrb) {

   }

   @Override
   public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

   }

   @Override
   public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

   }

   @Override
   public int onHeal(int healAmount, AmuletOrb paramOrb) {
     return 0;
   }
 }

