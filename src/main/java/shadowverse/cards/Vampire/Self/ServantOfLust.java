 package shadowverse.cards.Vampire.Self;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;


 public class ServantOfLust
   extends CustomCard
 {
   public static final String ID = "shadowverse:ServantOfLust";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ServantOfLust");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ServantOfLust.png";

   public ServantOfLust() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 3;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeMagicNumber(1);
     } 
   }

     @Override
     public void triggerOnExhaust() {
         for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
             if (!mo.isDeadOrEscaped()){
                 addToBot((AbstractGameAction)new LoseHPAction(mo,AbstractDungeon.player,this.magicNumber));
             }
         }
     }


     public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("ServantOfLust"));
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
       addToBot((AbstractGameAction)new LoseHPAction(p,p,1));
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           if (!mo.isDeadOrEscaped()){
               addToBot((AbstractGameAction)new LoseHPAction(mo,p,this.magicNumber));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ServantOfLust();
   }
 }

