 package shadowverse.cards.Dragon.Armed;
 


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import shadowverse.cards.Neutral.Temp.Motorbike;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class WyrmfireEngineer extends CustomCard {
   public static final String ID = "shadowverse:WyrmfireEngineer";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WyrmfireEngineer");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WyrmfireEngineer.png";

   public WyrmfireEngineer() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 4;
     this.cardsToPreview = new Motorbike();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("WyrmfireEngineer"));
     boolean overflow = false;
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)) {
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0) {
         overflow = true;
       }
     }
     if (overflow){
       addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new VigorPower(abstractPlayer, 5), 5));
     }else {
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       c.setCostForTurn(1);
       c.cost = 1;
       addToBot(new MakeTempCardInHandAction(c));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WyrmfireEngineer();
   }
 }

