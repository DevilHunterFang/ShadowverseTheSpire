 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.action.NaturalDiscardAction;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;
import shadowverse.powers.ShipsbanePlesiosaurusPower;


 public class ShipsbanePlesiosaurus
   extends CustomCard {
   public static final String ID = "shadowverse:ShipsbanePlesiosaurus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShipsbanePlesiosaurus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ShipsbanePlesiosaurus.png";

   public ShipsbanePlesiosaurus() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.cardsToPreview = new EvolutionPoint();
     this.baseBlock = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE, true),0.7f));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ShipsbanePlesiosaurusPower(abstractPlayer)));
     if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
       TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
       if (p.amount2 > 0){
         addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
       }
     }
     if (upgraded){
       addToBot(new NaturalDiscardAction(abstractPlayer,abstractPlayer,1,false,false,
               new GainBlockAction(abstractPlayer,this.block)));
       addToBot(new DrawCardAction(2));
       addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShipsbanePlesiosaurus();
   }
 }

