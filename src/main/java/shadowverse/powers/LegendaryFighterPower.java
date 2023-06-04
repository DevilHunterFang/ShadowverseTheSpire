 package shadowverse.powers;


 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.ArtifactPower;
 import com.megacrit.cardcrawl.powers.DoubleTapPower;
 import com.megacrit.cardcrawl.powers.EnvenomPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
 import shadowverse.cards.Neutral.Temp.LegendaryFighterA;


 public class LegendaryFighterPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:LegendaryFighterPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:LegendaryFighterPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public boolean attackCheck = true;
   public boolean skillCheck = true;
   public boolean powerCheck = true;

   public LegendaryFighterPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/LegendaryFighterPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
     if (damageAmount > 0)
       addToTop((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
     return damageAmount;
   }

   @Override
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     if (card.type == AbstractCard.CardType.ATTACK && attackCheck){
         addToBot((AbstractGameAction)new SFXAction("LegendaryFighterPower"));
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)AbstractDungeon.player, (AbstractGameEffect)new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DoubleTapPower((AbstractCreature)AbstractDungeon.player, 1), 1));
       attackCheck = false;
     }else if (card.type == AbstractCard.CardType.SKILL && skillCheck){
         addToBot((AbstractGameAction)new SFXAction("LegendaryFighterPower"));
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)AbstractDungeon.player, (AbstractGameEffect)new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new EnvenomPower((AbstractCreature)AbstractDungeon.player, 1), 1));
       skillCheck = false;
     }else if (card.type == AbstractCard.CardType.POWER && powerCheck){
         addToBot((AbstractGameAction)new SFXAction("LegendaryFighterPower"));
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)AbstractDungeon.player, (AbstractGameEffect)new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new ArtifactPower((AbstractCreature)AbstractDungeon.player, 1), 1));
       powerCheck = false;
     }
   }

   @Override
   public void atStartOfTurn() {
     AbstractCard c = (AbstractCard) new LegendaryFighterA();
     if (!attackCheck && !skillCheck && !powerCheck){
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), this.amount));
     }
     addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:LegendaryFighterPower"));
   }
 }


