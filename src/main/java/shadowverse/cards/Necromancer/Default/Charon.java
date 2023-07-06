 package shadowverse.cards.Necromancer.Default;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ImaginationAction;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;


 public class Charon extends CustomCard {
   public static final String ID = "shadowverse:Charon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Charon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Charon.png";

   public Charon() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 10;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Charon"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new NecromanceAction(5,null,new DrawCardAction(1, new ImaginationAction(this.upgraded))));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Charon();
   }
 }

