 package shadowverse.cards.Neutral.Temp;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BlurPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.cards.AbstractVehicleCard;


 public class Sleipnir
   extends AbstractVehicleCard
 {
   public static final String ID = "shadowverse:Sleipnir";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Sleipnir");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Sleipnir.png";

   public Sleipnir() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseBlock = 9;
     this.baseDamage = 9;
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
     this.predicate = card -> {
         return card.type==CardType.ATTACK&&card.costForTurn>=1;
     };
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeDamage(3);
       upgradeMagicNumber(1);
     } 
   }

     public boolean canUse(AbstractPlayer p, AbstractMonster m) {
         boolean canUse = super.canUse(p, m);
         if (!canUse)
             return false;
         if (!this.maneuver) {
             canUse = false;
         }
         return canUse;
     }

     public void triggerOnGlowCheck() {
         boolean glow = true;
         if (!this.maneuver) {
             glow = false;
         }
         if (glow) {
             this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
         } else {
             this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
         }
     }

     @Override
     public void triggerOnOtherCardPlayed(AbstractCard c){
       if (c.costForTurn>=1&&c.type==CardType.ATTACK&&!this.maneuver){
           this.maneuver = true;
           flash();
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
           this.cardsToPreview = c.makeStatEquivalentCopy();
           applyPowers();
       }
     }

     @Override
     public void triggerOnExhaust(){
       if (this.cardsToPreview!=null){
           AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
           c.setCostForTurn(0);
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
           this.cardsToPreview = null;
           applyPowers();
           this.maneuver = false;
       }
     }

   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
       addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new BlurPower(p,1),1));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Sleipnir();
   }
 }

