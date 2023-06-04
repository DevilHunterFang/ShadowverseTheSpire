 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 
 
 
 public class Mysteria
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Mysteria";
   public static final String IMG = "img/relics/Mysteria.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Mysteria_Outline.png";
   public static AbstractCard c = (AbstractCard)new shadowverse.cards.Neutral.Temp.Mysteria();
 
   
   public Mysteria() {
     super("shadowverse:Mysteria", ImageMaster.loadImage("img/relics/Mysteria.png"), RelicTier.SHOP, LandingSound.MAGICAL);
     this.counter = 0;
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
   }
   
   public void onEquip() {
     this.counter = 0;
   }
   
   public void atBattleStart() {
     if (this.counter > 0) {
       flash();
       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, this.counter), this.counter));
       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     } 
     addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c, 1, true, true));
     this.description = this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
     this.tips.clear();
     this.tips.add(new PowerTip(this.name, this.description));
     initializeTips();
   }
 
   
   public void onPlayCard(AbstractCard c, AbstractMonster m) {
      if (c.cardID.equals(Mysteria.c.cardID)) {
       this.counter += c.magicNumber;
       this.description = this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
       this.tips.clear();
       this.tips.add(new PowerTip(this.name, this.description));
       initializeTips();
     } 
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Mysteria();
   }
 }


