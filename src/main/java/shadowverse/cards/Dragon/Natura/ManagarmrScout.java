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


 public class ManagarmrScout extends CustomCard {
   public static final String ID = "shadowverse:ManagarmrScout";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ManagarmrScout");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ManagarmrScout.png";

   public ManagarmrScout() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.cardsToPreview = new NaterranGreatTree();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("ManagarmrScout"));
     addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ManagarmrScout();
   }
 }

