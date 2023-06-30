 package shadowverse.cards.Dragon.Ramp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Dragon;


 public class Dagon extends CustomCard {
   public static final String ID = "shadowverse:Dagon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Dagon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Dagon.png";

   public Dagon() {
     super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 18;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
       upgradeMagicNumber(1);
     } 
   }

   public void applyPowers() {
     AbstractPower strength = AbstractDungeon.player.getPower("Strength");
     if (strength != null)
       strength.amount *= this.magicNumber;
     super.applyPowers();
     if (strength != null)
       strength.amount /= this.magicNumber;
   }

   public void calculateCardDamage(AbstractMonster mo) {
     AbstractPower strength = AbstractDungeon.player.getPower("Strength");
     if (strength != null)
       strength.amount *= this.magicNumber;
     super.calculateCardDamage(mo);
     if (strength != null)
       strength.amount /= this.magicNumber;
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Dagon"));
     addToBot(new VFXAction(new BorderFlashEffect(Color.BLUE, true),0.7f));
     for (int i = 0 ; i < 3; i++){
       addToBot(new VFXAction(new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.CYAN.cpy()), 0.1F));
       addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Dagon();
   }
 }

