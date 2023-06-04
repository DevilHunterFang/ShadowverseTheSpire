 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.SpellBoostAction;
import shadowverse.characters.Witchcraft;


 public class DemonicShikigami
   extends CustomCard
 {
   public static final String ID = "shadowverse:DemonicShikigami";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DemonicShikigami");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DemonicShikigami.png";

   public DemonicShikigami() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 18;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("KuonA"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     addToBot((AbstractGameAction)new SpellBoostAction(abstractPlayer, (AbstractCard)this, abstractPlayer.hand.group));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DemonicShikigami();
   }
 }

