 package shadowverse.cards.Elf.Wood;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import shadowverse.action.MatriarchDamageAction;
import shadowverse.characters.Elf;


 public class WildwoodMatriarch extends CustomCard {
   public static final String ID = "shadowverse:WildwoodMatriarch";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WildwoodMatriarch");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WildwoodMatriarch.png";

   public WildwoodMatriarch() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 10;
     this.cardsToPreview = (AbstractCard)new GreenWoodGuardian();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("WildwoodMatriarch"));
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
     int count = 0;
     for (AbstractCard ca : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
       if (ca instanceof GreenWoodGuardian)
         count++;
     }
     if (count >= 3){
       addToBot((AbstractGameAction)new MatriarchDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     }else {
       addToBot((AbstractGameAction)new MatriarchDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WildwoodMatriarch();
   }
 }

