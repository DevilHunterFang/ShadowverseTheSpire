 package shadowverse.cards.Vampire.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.actions.common.DrawExptCardAction;
import shadowverse.characters.Vampire;


 public class MaliciousBlader extends CustomCard {
   public static final String ID = "shadowverse:MaliciousBlader";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MaliciousBlader");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MaliciousBlader.png";

   public MaliciousBlader() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 8;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(8);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new GainEnergyAction(1));
     addToBot(new DrawExptCardAction(1,card -> card.type == CardType.ATTACK && card.color == Vampire.Enums.COLOR_SCARLET && card.cost == 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MaliciousBlader();
   }
 }

