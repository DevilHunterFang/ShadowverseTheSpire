 package shadowverse.cards.Dragon.Natura;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag_NOREPEAT;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.NaterranTree;


 public class NewfoundAllies
   extends CustomCard {
   public static final String ID = "shadowverse:NewfoundAllies";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewfoundAllies");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NewfoundAllies.png";

   public NewfoundAllies() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("NewfoundAllies"));
     addToBot(new DrawPileToHandAction_Tag_NOREPEAT(this.magicNumber, AbstractShadowversePlayer.Enums.NATURAL,null,this));
     if (abstractPlayer.hasPower(NaterranTree.POWER_ID)){
       addToBot(new DrawPileToHandAction_Tag_NOREPEAT(1, AbstractShadowversePlayer.Enums.NATURAL,null,this));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NewfoundAllies();
   }
 }

