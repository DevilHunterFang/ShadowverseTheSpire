 package shadowverse.cards.Dragon.Discard2;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;


 public class WiseDragonewtScholar
   extends CustomCard {
   public static final String ID = "shadowverse:WiseDragonewtScholar";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WiseDragonewtScholar");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WiseDragonewtScholar.png";

   public WiseDragonewtScholar() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 8;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("WiseDragonewtScholar"));
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
     addToBot(new DrawCardAction(1));
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     if (upgraded){
       addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false));
       addToBot(new DrawCardAction(1));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WiseDragonewtScholar();
   }
 }

