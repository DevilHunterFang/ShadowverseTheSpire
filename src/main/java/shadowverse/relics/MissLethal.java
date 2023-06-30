 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;



 public class MissLethal
   extends CustomRelic
 {
   public static final String ID = "shadowverse:MissLethal";
   public static final String IMG = "img/relics/MissLethal.png";
   public static final String OUTLINE_IMG = "img/relics/outline/MissLethal_Outline.png";
   public boolean lifeCheck;

   public MissLethal() {
     super("shadowverse:MissLethal", ImageMaster.loadImage("img/relics/MissLethal.png"), RelicTier.BOSS, LandingSound.SOLID);
   }

   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void onEquip() {
     AbstractDungeon.player.energy.energyMaster++;
   }

   public void onUnequip() {
     AbstractDungeon.player.energy.energyMaster--;
   }

   public void atBattleStart() {
     flash();
     addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
     this.lifeCheck = true;
   }

   public void atTurnStart() {
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       if (mo.currentHealth <= mo.maxHealth / 5 && !mo.isDead && !mo.isDeadOrEscaped() && !mo.hasPower("Minion") && this.lifeCheck) {
         addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new IntangiblePlayerPower(mo, 1), 1));
         this.lifeCheck = false;
       }
     }
   }


   public AbstractRelic makeCopy() {
     return new MissLethal();
   }
 }
 