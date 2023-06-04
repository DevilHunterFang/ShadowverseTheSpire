 package shadowverse.cards.Elf.Long;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;
import shadowverse.powers.AlbertaPower;


 public class Alberta extends CustomCard {
   public static final String ID = "shadowverse:Alberta";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Alberta");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Alberta.png";

   public Alberta() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 6;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
     addToBot((AbstractGameAction)new SFXAction("Alberta"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new AlbertaPower(abstractPlayer,this.upgraded)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Alberta();
   }
 }

