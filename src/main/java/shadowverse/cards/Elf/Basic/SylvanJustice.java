 package shadowverse.cards.Elf.Basic;
 


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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.characters.Elf;


 public class SylvanJustice extends CustomCard {
   public static final String ID = "shadowverse:SylvanJustice";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SylvanJustice");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/SylvanJustice.png";

   public SylvanJustice() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.BASIC, CardTarget.ENEMY);
     this.baseDamage = 5;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new Fairy();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("SylvanJustice"));
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,this.magicNumber));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SylvanJustice();
   }
 }

