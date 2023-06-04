 package shadowverse.cards.Neutral.Temp;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
 import shadowverse.powers.AstarothLimit;


 public class Astaroth
   extends CustomCard
 {
   public static final String ID = "shadowverse:Astaroth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Astaroth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Astaroth.png";
   
   public Astaroth() {
     super("shadowverse:Astaroth", NAME, "img/cards/Astaroth.png", 5, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(4);
     } 
   }

     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         int count = 0;
         for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
             count++;
         }
         boolean canUse = super.canUse(p, m);
         if (!canUse)
             return false;
         if (count > 1) {
             canUse = false;
         }
         return canUse;
     }

     public void triggerOnGlowCheck() {
         int count = 0;
         for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
             count++;
         }
         boolean glow = true;
         if (count > 1) {
             glow = false;
         }
         if (glow) {
             this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
         } else {
             this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
         }
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Settings.FAST_MODE) {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 0.7F));
     } else {
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 1.0F));
     } 
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       int astarothDamage = mo.currentBlock + mo.currentHealth - 1;
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)abstractPlayer, astarothDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer,(AbstractCreature)abstractPlayer, (AbstractPower)new AstarothLimit(abstractPlayer)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Astaroth();
   }
 }
