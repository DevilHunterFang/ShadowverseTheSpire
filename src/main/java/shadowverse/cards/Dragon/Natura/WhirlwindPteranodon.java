 package shadowverse.cards.Dragon.Natura;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.NaterranTree;
import shadowverse.powers.OverflowPower;


 public class WhirlwindPteranodon extends CustomCard {
   public static final String ID = "shadowverse:WhirlwindPteranodon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WhirlwindPteranodon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WhirlwindPteranodon.png";

   public WhirlwindPteranodon() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 4;
     this.cardsToPreview = new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,abstractPlayer,this.block));
     if (abstractPlayer.hasPower(NaterranTree.POWER_ID)){
       for (AbstractOrb o:abstractPlayer.orbs){
         if (o instanceof AmuletOrb){
           if (((AmuletOrb) o).amulet instanceof NaterranGreatTree){
             addToBot( new EvokeSpecificOrbAction(o));
           }
         }
       }
       addToBot(new RemoveSpecificPowerAction(abstractPlayer, abstractPlayer, "shadowverse:NaterranTree"));
       addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
     }
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WhirlwindPteranodon();
   }
 }

