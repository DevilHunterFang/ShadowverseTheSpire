 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import shadowverse.characters.Dragon;


 public class DragoonMedic extends CustomCard {
   public static final String ID = "shadowverse:DragoonMedic";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragoonMedic");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DragoonMedic.png";
   
   public DragoonMedic() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 12;
     this.baseMagicNumber = 6;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }

   @Override
   public void triggerOnManualDiscard() {
     addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new NextTurnBlockPower(AbstractDungeon.player,this.magicNumber),this.magicNumber));
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("DragoonMedic"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new NextTurnBlockPower(abstractPlayer,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DragoonMedic();
   }
 }

