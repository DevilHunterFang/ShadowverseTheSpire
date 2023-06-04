 package shadowverse.cards.Bishop.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import shadowverse.characters.Bishop;


 public class ThemisDecree
   extends CustomCard {
   public static final String ID = "shadowverse:ThemisDecree";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ThemisDecree");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ThemisDecree.png";

   public ThemisDecree() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c.type != CardType.SKILL && c.type != CardType.POWER)
         addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
     }
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       if (mo != null && !mo.isDeadOrEscaped()){
         addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
         addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       }
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ThemisDecree();
   }
 }

