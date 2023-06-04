 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Nemesis;


 public class VoidRealize
   extends CustomCard
 {
   public static final String ID = "shadowverse:VoidRealize";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VoidRealize");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/VoidRealize.png";

   public VoidRealize() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
     this.exhaust = true;
     this.selfRetain = true;
     this.cardsToPreview = (AbstractCard)new Wretches();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("VoidRealize"));
       AbstractCard w = (AbstractCard)new Wretches();
       int exhaustAmt = 0;
       for (AbstractCard c:abstractPlayer.exhaustPile.group){
           if (c.type==CardType.ATTACK)
               exhaustAmt++;
       }
       if (exhaustAmt>=20){
           w.baseDamage *= 2;
           w.applyPowers();
       }
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(w,3));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new VoidRealize();
   }
 }

