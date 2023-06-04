 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.HeavenlyAegisPower;


 public class RelicSphere
   extends CustomCard implements AbstractNoCountDownAmulet {
   public static final String ID = "shadowverse:RelicSphere";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RelicSphere");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RelicSphere.png";

   public RelicSphere() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new RelicGod();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot((AbstractGameAction)new SFXAction("RelicSphere"));
     if (m != null && !m.isDeadOrEscaped()) {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.SKY.cpy(),Color.WHITE.cpy(),"HEAL_3")));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new FrailPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
     }
     boolean hasGod = false;
     for (AbstractOrb o :p.orbs){
       if (o instanceof AmuletOrb){
         if (((AmuletOrb) o).amulet instanceof RelicGod){
           hasGod = true;
           break;
         }
       }
     }
     if (hasGod){
       addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)m, 27));
     }else {
       addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)m, 9));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RelicSphere();
   }

   @Override
   public void onStartOfTurn(AmuletOrb paramOrb) {

   }

   @Override
   public void onEvoke(AmuletOrb paramOrb) {

   }

   @Override
   public void endOfTurn(AmuletOrb paramOrb) {
     boolean hasPrism = false;
     boolean hasTorus = false;
     boolean hasPlaton = false;
     for (AbstractOrb o: AbstractDungeon.player.orbs){
       if (o instanceof AmuletOrb){
         if (((AmuletOrb) o).amulet instanceof RelicGod){
           break;
         } else if (((AmuletOrb) o).amulet instanceof RelicSphere && o!=paramOrb) {
           break;
         } else {
           if (((AmuletOrb) o).amulet instanceof RelicPrism)
             hasPrism = true;
           if (((AmuletOrb) o).amulet instanceof RelicTorus)
             hasTorus = true;
           if (((AmuletOrb) o).amulet instanceof RelicPlaton)
             hasPlaton = true;
         }
       }
     }
     if (hasPrism && hasTorus && hasPlaton){
       for (AbstractOrb o: AbstractDungeon.player.orbs){
         if (o instanceof AmuletOrb){
             if (((AmuletOrb) o).amulet instanceof RelicPrism || ((AmuletOrb) o).amulet instanceof RelicTorus ||((AmuletOrb) o).amulet instanceof RelicPlaton )
               addToBot((AbstractGameAction)new EvokeSpecificOrbAction(o));
         }
       }
       addToBot((AbstractGameAction)new EvokeSpecificOrbAction(paramOrb));
       addToBot((AbstractGameAction)new PlaceAmulet(this.cardsToPreview.makeStatEquivalentCopy(),null));
       addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new HeavenlyAegisPower(AbstractDungeon.player)));
     }
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

