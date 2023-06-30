 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;
import shadowverse.powers.NoirNBlancPower;


 public class NoirNBlanc extends CustomCard {
   public static final String ID = "shadowverse:NoirNBlanc";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NoirNBlanc");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NoirNBlanc.png";
   
   public NoirNBlanc() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 20;
     this.baseMagicNumber = 30;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
       upgradeMagicNumber(6);
     } 
   }

   @Override
   public void triggerOnManualDiscard() {
     addToBot(new SFXAction("NoirNBlanc_Eff"));
     addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 6, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new NoirNBlancPower(AbstractDungeon.player,this.magicNumber)));
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("NoirNBlanc"));
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new NoirNBlancPower(abstractPlayer,this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NoirNBlanc();
   }
 }

