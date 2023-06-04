 package shadowverse.cards.Neutral.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Status.EvolutionPoint;

 public class WrathfulIcefiend
   extends CustomCard {
   public static final String ID = "shadowverse:WrathfulIcefiend";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WrathfulIcefiend");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WrathfulIcefiend.png";
   
   public WrathfulIcefiend() {
     super("shadowverse:WrathfulIcefiend", NAME, "img/cards/WrathfulIcefiend.png", 1, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 8;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("WrathfulIcefiend"));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint(),2));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WrathfulIcefiend();
   }
 }

