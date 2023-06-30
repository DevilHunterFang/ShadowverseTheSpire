 package shadowverse.cards.Dragon.Basic;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;


 public class Defend_D
   extends CustomCard {
   public static final String ID = "shadowverse:Defend_D";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Defend_D");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Defend_D.png";
   
   public Defend_D() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.BASIC, CardTarget.SELF);
     this.baseBlock = 5;
     this.tags.add(CardTags.STARTER_DEFEND);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Defend_D();
   }
 }

