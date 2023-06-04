 package shadowverse.cards.Vampire.Vengeance;


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
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.characters.Vampire;
 import shadowverse.powers.EpitaphPower;
 import shadowverse.stance.Vengeance;


 public class Emeralda extends CustomCard {
   public static final String ID = "shadowverse:Emeralda";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Emeralda");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Emeralda.png";

   public Emeralda() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 20;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Emeralda"));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,(AbstractPower)new VulnerablePower(abstractMonster,this.magicNumber,false),this.magicNumber));
     addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,(AbstractPower)new WeakPower(abstractMonster,this.magicNumber,false),this.magicNumber));
     if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)||abstractPlayer.hasPower(EpitaphPower.POWER_ID)){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     }else
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Emeralda();
   }
 }

