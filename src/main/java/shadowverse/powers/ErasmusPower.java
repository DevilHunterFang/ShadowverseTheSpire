 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.characters.AbstractShadowversePlayer;

















 
 public class ErasmusPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:ErasmusPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ErasmusPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public ErasmusPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:ErasmusPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/ErasmusPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       addToBot(new SFXAction("ErasmusPower"));
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         if (!mo.isDeadOrEscaped())
           addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F)); 
       } 
       addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
       AbstractPlayer p = (AbstractPlayer)this.owner;
       boolean powerExists = false;
       for (AbstractPower pow : p.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           powerExists = true;
           break;
         } 
       } 
       if (powerExists) {
         ((AbstractShadowversePlayer)p).earthCount++;
         flash();
         for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           if (!mo.isDeadOrEscaped())
             addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F)); 
         } 
         addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
         addToBot(new ApplyPowerAction(this.owner, this.owner, new EarthEssence(this.owner, -1), -1));
       } 
     } 
   }
 }

