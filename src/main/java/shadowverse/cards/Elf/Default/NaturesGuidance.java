 package shadowverse.cards.Elf.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BounceAction;
import shadowverse.characters.Elf;

 
 public class NaturesGuidance extends CustomCard {
   public static final String ID = "shadowverse:NaturesGuidance";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NaturesGuidance");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NaturesGuidance.png";
   
   public NaturesGuidance() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
     boolean canUse = super.canUse(p, m);
     if (!canUse)
       return false;
     boolean hasAttack = false;
     for (AbstractCard c : p.discardPile.group) {
       if (c.type == AbstractCard.CardType.ATTACK || c.type == CardType.POWER)
         hasAttack = true;
     }
     if (!hasAttack) {
       this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
       canUse = false;
     }
     return canUse;
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("NaturesGuidance"));
       addToBot((AbstractGameAction)new BounceAction(1));
       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NaturesGuidance();
   }
 }
