 package shadowverse.cards.Nemesis.Default;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Nemesis;

 public class MindDivider
   extends CustomCard {
   public static final String ID = "shadowverse:MindDivider";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MindDivider");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MindDivider.png";

   public MindDivider() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
     this.isEthereal = true;
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isEthereal = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("MindDivider"));
       int amt = 0;
       for (AbstractCard c:abstractPlayer.hand.group){
           if (c.type==CardType.ATTACK&&c!=this)
               amt++;
       }
       addToBot((AbstractGameAction)new GainEnergyAction(amt));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MindDivider();
   }
 }

