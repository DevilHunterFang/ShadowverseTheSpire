 package shadowverse.cards.Nemesis.Mech;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
 import shadowverse.powers.BearminatorPower;


 public class Bearminator extends CustomCard {
   public static final String ID = "shadowverse:Bearminator";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Bearminator");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Bearminator.png";

   public Bearminator() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 18;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.baseMagicNumber = 9;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
       upgradeMagicNumber(3);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
       for (AbstractCard c : abstractPlayer.hand.group){
           if (c.type==CardType.ATTACK&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
               addToBot(new GainEnergyAction(1));
           }
       }
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new BearminatorPower(abstractPlayer,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Bearminator();
   }
 }
