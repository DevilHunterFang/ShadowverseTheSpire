 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.LumiorePower;
import shadowverse.powers.OverflowPower;


 public class Lumiore
   extends CustomCard {
   public static final String ID = "shadowverse:Lumiore";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lumiore");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lumiore.png";

   public Lumiore() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 20;
     this.baseMagicNumber = 8;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
       upgradeMagicNumber(2);
     } 
   }

   public void applyPowers() {
     super.applyPowers();
     int count = 0;
     if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
       count = ((AbstractShadowversePlayer) AbstractDungeon.player).discardCount;
     }
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Lumiore"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new GainEnergyAction(1));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LumiorePower(abstractPlayer,this.magicNumber),this.magicNumber));
     if (abstractPlayer instanceof AbstractShadowversePlayer){
       if (((AbstractShadowversePlayer) abstractPlayer).discardCount > 3){
         addToBot(new DiscardAction(abstractPlayer,abstractPlayer,2,false));
         addToBot(new DrawCardAction(3));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lumiore();
   }
 }

