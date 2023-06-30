 package shadowverse.cards.Dragon.Tempo;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Dragon;


 public class ArdentTorch extends CustomCard {
   public static final String ID = "shadowverse:ArdentTorch";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArdentTorch");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ArdentTorch.png";

   public ArdentTorch() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.NONE);
     this.cardsToPreview = new Burn();
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("ArdentTorch"));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
     addToBot(new DrawCardAction(1));
     for (AbstractCard c : abstractPlayer.hand.group){
       if (c instanceof Burn)
         addToBot(new DrawCardAction(1));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ArdentTorch();
   }
 }

