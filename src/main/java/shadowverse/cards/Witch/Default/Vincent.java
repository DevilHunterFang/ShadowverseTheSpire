 package shadowverse.cards.Witch.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import shadowverse.cards.Neutral.Temp.JudgmentWord;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.VincentPower;


 public class Vincent
   extends CustomCard
 {
   public static final String ID = "shadowverse:Vincent";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vincent");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Vincent.png";
   
   public Vincent() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new JudgmentWord();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isInnate = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     }
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:VincentPower")) {
         powerExists = true;
         break;
       } 
     } 
     if (!powerExists) {
       addToBot((AbstractGameAction)new SFXAction("Vincent"));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new TimeWarpTurnEndEffect(), 0.0F));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new VincentPower((AbstractCreature) abstractPlayer)));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Vincent();
   }
 }


