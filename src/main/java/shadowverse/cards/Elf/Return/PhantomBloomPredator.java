 package shadowverse.cards.Elf.Return;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.characters.Elf;


 public class PhantomBloomPredator extends CustomCard {
   public static final String ID = "shadowverse:PhantomBloomPredator";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PhantomBloomPredator");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PhantomBloomPredator.png";

   public PhantomBloomPredator() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseDamage = 8;
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {
     boolean hasBounceTarget = false;
     for (AbstractCard c:abstractPlayer.discardPile.group){
       if (c.cost<=1&&c.type==CardType.ATTACK){
         hasBounceTarget = true;
         break;
       }
     }
     if (hasBounceTarget){
       addToBot((AbstractGameAction)new MoveCardsAction(abstractPlayer.hand,abstractPlayer.discardPile, card -> {
         return card.cost<=1&&card.type==CardType.ATTACK;
       },1,abstractCards -> {
         if (m!=null)
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(m.hb.cX, m.hb.cY, Color.FOREST, Color.WHITE), 0.1F));
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.damage+this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
       }));
     }else {
       if (m!=null)
         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(m.hb.cX, m.hb.cY, Color.FOREST, Color.WHITE), 0.1F));
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
     }
     addToBot((AbstractGameAction)new SFXAction("PhantomBloomPredator"));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PhantomBloomPredator();
   }
 }

