 package shadowverse.cards.Dragon.Armed;
 


import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.ArmedMod;
import shadowverse.cards.Neutral.Temp.DragonWeapon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


 public class ArmorBurst
   extends CustomCard {
   public static final String ID = "shadowverse:ArmorBurst";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArmorBurst");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ArmorBurst.png";
   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
   
   public ArmorBurst() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.NONE);
     this.cardsToPreview = new DragonWeapon();
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
     boolean canUse = super.canUse(p, m);
     if (!canUse)
       return false;
     boolean hasAttack = false;
     for (AbstractCard c : p.discardPile.group) {
       if (c.type == AbstractCard.CardType.ATTACK && c.hasTag(AbstractShadowversePlayer.Enums.ARMED))
         hasAttack = true;
     }
     if (!hasAttack) {
       this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
       canUse = false;
     }
     return canUse;
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new MoveCardsAction(abstractPlayer.hand, abstractPlayer.discardPile, card -> {
       return card.type == CardType.ATTACK && card.hasTag(AbstractShadowversePlayer.Enums.ARMED);
     }, 1,abstractCards -> {
       for (AbstractCard c : abstractCards) {
         addToBot(new ReduceCostForTurnAction(c,1));
         c.superFlash();
       }
     }));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ArmorBurst();
   }
 }

