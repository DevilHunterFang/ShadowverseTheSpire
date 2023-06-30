 package shadowverse.cards.Bishop.Recover2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


 public class Lou_Bishop extends CustomCard {
   public static final String ID = "shadowverse:Lou_Bishop";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lou_Bishop");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lou_Bishop.png";

   public Lou_Bishop() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 3;
     this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     addToBot(new SFXAction("Lou_Bishop"));
     addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     AbstractPower s = p.getPower(StrengthPower.POWER_ID);
     AbstractPower d = p.getPower(DexterityPower.POWER_ID);
     if ((s!=null && s.amount>0) || (d!=null && d.amount>0)){
       addToBot(new HealAction(p,p,1));
       addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lou_Bishop();
   }
 }

