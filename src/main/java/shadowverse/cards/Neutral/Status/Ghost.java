 package shadowverse.cards.Neutral.Status;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MasqueradeGhostPower;


 public class Ghost
   extends CustomCard {
   public static final String ID = "shadowverse:Ghost";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ghost");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ghost.png";

   public Ghost() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.STATUS, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
     if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(MasqueradeGhostPower.POWER_ID)){
       this.baseDamage = 4 + (AbstractDungeon.player.getPower(MasqueradeGhostPower.POWER_ID)).amount;
     } else{
       this.baseDamage = 4;
     }
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Ghost"));
     addToBot(new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ghost();
   }
 }

