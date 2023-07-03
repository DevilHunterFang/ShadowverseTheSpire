 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.LavateinnDragonDefenseFormPower;


 public class LavateinnDragonDefenseForm extends CustomCard {
   public static final String ID = "shadowverse:LavateinnDragonDefenseForm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LavateinnDragonDefenseForm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/LavateinnDragonDefenseForm.png";

   public LavateinnDragonDefenseForm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 18;
     this.tags.add(AbstractShadowversePlayer.Enums.ARMED);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     addToBot(new DrawCardAction(2));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LavateinnDragonDefenseFormPower(abstractPlayer,2),2));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LavateinnDragonDefenseForm();
   }
 }

