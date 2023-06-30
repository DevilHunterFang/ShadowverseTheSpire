 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class Argente
   extends CustomCard {
   public static final String ID = "shadowverse:Argente";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Argente");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Argente.png";

   public Argente() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 12;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
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
     addToBot(new SFXAction("Argente"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,2,false));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
     if (abstractPlayer instanceof AbstractShadowversePlayer){
       if (((AbstractShadowversePlayer) abstractPlayer).discardCount > 1){
         addToBot(new DrawCardAction(3));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Argente();
   }
 }

