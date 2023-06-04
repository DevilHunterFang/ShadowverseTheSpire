 package shadowverse.cards.Nemesis.Puppet;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Puppet;
import shadowverse.characters.Nemesis;


 public class Noa extends CustomCard {
   public static final String ID = "shadowverse:Noa";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Noa");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Noa.png";

   public Noa() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 18;
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new Puppet();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Noa"));
     AbstractCard pup = this.cardsToPreview.makeStatEquivalentCopy();
     pup.baseDamage += this.magicNumber;
     pup.applyPowers();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(pup));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c instanceof Puppet) {
         c.baseDamage += this.magicNumber;
         c.applyPowers();
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Noa();
   }
 }

