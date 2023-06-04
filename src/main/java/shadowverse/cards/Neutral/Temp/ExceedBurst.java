 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


 public class ExceedBurst
   extends CustomCard {
   public static final String ID = "shadowverse:ExceedBurst";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ExceedBurst");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ExceedBurst.png";

   public ExceedBurst() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 6;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     addToBot((AbstractGameAction)new SFXAction("ExceedBurst"));
     int drawPileAmt = abstractPlayer.drawPile.group.size();
     int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
     if (drawPileAmt <= masterDeckAmt/4){
       this.damage *=3;
     }else if (drawPileAmt <= masterDeckAmt/2){
       this.damage *= 2;
     }
     addToBot((AbstractGameAction)new WaitAction(0.8F));
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ExceedBurst();
   }
 }

