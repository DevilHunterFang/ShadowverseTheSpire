 package shadowverse.cards.Neutral.Temp;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import shadowverse.cards.AbstractVehicleCard;
 import shadowverse.cards.Nemesis.Tokens.ICCard;
 import shadowverse.characters.Nemesis;


 public class MagiTrainCard
   extends AbstractVehicleCard
 {
   public static final String ID = "shadowverse:MagiTrainCard";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagiTrainCard");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MagiTrainCard.png";

   public MagiTrainCard() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 27;
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.selfRetain = true;
     this.predicate = card -> {
         return card.type==CardType.ATTACK&&card.costForTurn>=2;
     };
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(9);
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
       if (c.costForTurn>=2&&c.type==CardType.ATTACK&&!this.maneuver){
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
       addToBot((AbstractGameAction)new SFXAction("MagiTrain"));
       addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
       for(AbstractCard c:p.hand.group){
           if (!(c instanceof ICCard)){
               addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
           }
       }
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MagiTrainCard();
   }
 }

