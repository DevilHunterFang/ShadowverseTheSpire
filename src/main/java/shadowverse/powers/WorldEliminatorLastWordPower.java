 package shadowverse.powers;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
 import shadowverse.cards.Temp.ManaForce;


 public class WorldEliminatorLastWordPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:WorldEliminatorLastWordPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WorldEliminatorLastWordPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public WorldEliminatorLastWordPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
       loadRegion("explosive");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
   }

   @Override
   public void onDeath() {
       DamageInfo damageInfo = new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS);
       if (Settings.FAST_MODE) {
           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.owner.hb.cX, this.owner.hb.cY), 0.1F));
       } else {
           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.owner.hb.cX, this.owner.hb.cY), 0.3F));
       }
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature) AbstractDungeon.player, damageInfo, AbstractGameAction.AttackEffect.NONE, true));
       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters){
           if (m.type == AbstractMonster.EnemyType.BOSS){
               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)m, (AbstractCreature)this.owner, this.amount));
           }
       }
   }
   
 }


