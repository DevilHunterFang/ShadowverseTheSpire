 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.characters.Nemesis;


 public class TechnologyMana
   extends CustomCard
 {
   public static final String ID = "shadowverse:TechnologyMana";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TechnologyMana");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TechnologyMana.png";

   public TechnologyMana() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
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
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TechnologyMana();
   }
 }

