 package shadowverse.cards.Necromancer.Ghosts;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.action.DestroyAction;
import shadowverse.characters.Necromancer;


 public class TrothCurse extends CustomCard {
   public static final String ID = "shadowverse:TrothCurse";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TrothCurse");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TrothCurse.png";

   public TrothCurse() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }

     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         boolean canUse = super.canUse(p, m);
         if (!canUse)
             return false;
         boolean hasAttack = false;
         for (AbstractCard c : p.discardPile.group) {
             if (c.type == CardType.ATTACK)
                 hasAttack = true;
         }
         if (!hasAttack) {
             this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
             canUse = false;
         }
         return canUse;
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new DestroyAction(1,null));
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TrothCurse();
   }
 }
