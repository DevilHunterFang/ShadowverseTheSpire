 package shadowverse.cards.Witch.Academic;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.CraigPower;


 public class Craig
   extends CustomCard {
   public static final String ID = "shadowverse:Craig";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Craig");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Craig.png";
   public static final int BASE_COST = 7;

   public Craig() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)) {
         flash();
         addToBot(new SFXAction("spell_boost"));
         addToBot(new ReduceCostAction(this));
       }
       if(c.type==CardType.SKILL){
         flash();
         addToBot(new SFXAction("spell_boost"));
         addToBot(new ReduceCostAction(this));
       }
   }

   
   public void upgrade() {
     if (!this.upgraded){
       upgradeName();
       upgradeMagicNumber(1);
     }
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     addToBot(new SFXAction("Craig"));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new CraigPower(abstractPlayer,this.magicNumber)));
   }
   
   public AbstractCard makeCopy() {
     return new Craig();
   }

 }


