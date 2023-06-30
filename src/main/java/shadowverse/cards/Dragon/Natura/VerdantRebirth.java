 package shadowverse.cards.Dragon.Natura;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.NaterranTree;


 public class VerdantRebirth extends CustomCard {
   public static final String ID = "shadowverse:VerdantRebirth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VerdantRebirth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/VerdantRebirth.png";

   public VerdantRebirth() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 9;
     this.baseMagicNumber = 6;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("VerdantRebirth"));
     int dmg = this.damage;
     if (abstractPlayer.hasPower(NaterranTree.POWER_ID)){
       dmg += this.magicNumber;
     }
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new VerdantRebirth();
   }
 }

