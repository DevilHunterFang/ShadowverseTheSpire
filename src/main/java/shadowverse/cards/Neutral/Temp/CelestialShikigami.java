 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.SpellBoostAction;
import shadowverse.characters.Witchcraft;


 public class CelestialShikigami
   extends CustomCard
 {
   public static final String ID = "shadowverse:CelestialShikigami";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CelestialShikigami");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/CelestialShikigami.png";

   public CelestialShikigami() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 25;
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     addToBot((AbstractGameAction)new SpellBoostAction(abstractPlayer, (AbstractCard)this, abstractPlayer.hand.group));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new CelestialShikigami();
   }
 }

