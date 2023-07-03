 package shadowverse.cards.Neutral.Temp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.characters.Dragon;


 public class IniquitousLindworm extends CustomCard {
   public static final String ID = "shadowverse:IniquitousLindworm";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IniquitousLindworm");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/IniquitousLindworm.png";

   public IniquitousLindworm() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 100;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(20);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new VFXAction(new BorderFlashEffect(Color.PURPLE, true),0.4f));
     addToBot(new SFXAction("ATTACK_HEAVY"));
     addToBot(new VFXAction(abstractPlayer, new MindblastEffect(abstractPlayer.dialogX, abstractPlayer.dialogY, abstractPlayer.flipHorizontal), 0.1F));
     if (abstractMonster.currentHealth>this.damage){
       abstractMonster.currentHealth -= Math.min(this.damage, abstractMonster.currentHealth - 1);
       abstractMonster.update();
     }else {
       addToBot(new JudgementAction(abstractMonster, this.damage));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new IniquitousLindworm();
   }
 }

