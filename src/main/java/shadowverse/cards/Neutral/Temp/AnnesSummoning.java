 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


 public class AnnesSummoning
   extends CustomCard {
   public static final String ID = "shadowverse:AnnesSummoning";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AnnesSummoning");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AnnesSummoning.png";

   public AnnesSummoning() {
     super(ID, NAME,IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 25;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
     this.exhaust = true;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
       ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
     }
     AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new AnnesSummoning();
   }
 }

