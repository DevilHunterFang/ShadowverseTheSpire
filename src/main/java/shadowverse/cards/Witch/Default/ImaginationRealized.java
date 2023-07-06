 package shadowverse.cards.Witch.Default;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.action.ImaginationAction;
 import shadowverse.characters.Witchcraft;

 public class ImaginationRealized extends CustomCard {
   public static final String ID = "shadowverse:ImaginationRealized";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImaginationRealized");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImaginationRealized.png";

   public ImaginationRealized() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot(new DrawCardAction(3, new ImaginationAction(this.upgraded)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ImaginationRealized();
   }
 }

