 package shadowverse.cards.Dragon.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.TidalTyranny;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class SiLong extends CustomCard {
   public static final String ID = "shadowverse:SiLong";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SiLong");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SiLong.png";

   public SiLong() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 12;
     this.cardsToPreview = new TidalTyranny();
     this.tags.add(AbstractShadowversePlayer.Enums.FES);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("SiLong"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new HealAction(abstractPlayer,abstractPlayer,3));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OverflowPower(abstractPlayer,1)));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SiLong();
   }
 }

