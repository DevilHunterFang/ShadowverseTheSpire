 package shadowverse.cards.Witch.Earth2;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

 import shadowverse.Shadowverse;
import shadowverse.action.BlockPerCardAction;
 import shadowverse.cards.Neutral.Temp.VeridicRitual;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;
 
 
 
 public class Clarke
   extends CustomCard
 {
   public static final String ID = "shadowverse:Clarke";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Clarke");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Clarke.png";
   public boolean doubleCheck = false;



   public Clarke() {
     super("shadowverse:Clarke", NAME, "img/cards/Clarke.png", 5, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 3;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new VeridicRitual();
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
           doubleCheck = true;
           if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
               setCostForTurn(0);
               this.type = CardType.SKILL;
               applyPowers();
           }
       }else {
           if (doubleCheck) {
               doubleCheck = false;
           }else {
               if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                   setCostForTurn(0);
                   this.type = CardType.SKILL;
                   applyPowers();
               }
           }
       }
   }

     public void triggerOnGlowCheck() {
         if (this.type == CardType.SKILL) {
             this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
         } else {
             this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
         }
     }


   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 5 && this.type != CardType.ATTACK) {
       resetAttributes();
       this.type = CardType.ATTACK;
       applyPowers();
     }
   }
   
   public void triggerWhenDrawn() {
     if (Shadowverse.Accelerate((AbstractCard)this)) {
       super.triggerWhenDrawn();
       setCostForTurn(0);
       this.type = CardType.SKILL;
     } else {
       this.type = CardType.ATTACK;
     } 
     applyPowers();
   }

   @Override
   public void atTurnStart() {
       if (AbstractDungeon.player.hand.group.contains(this)){
           if (EnergyPanel.getCurrentEnergy()<5) {
               setCostForTurn(0);
               this.type = CardType.SKILL;
           } else {
               resetAttributes();
               this.type = CardType.ATTACK;
           }
           applyPowers();
       }
   }

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.ATTACK;
         applyPowers();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       addToBot((AbstractGameAction)new SFXAction("Clarke_Accelerate"));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, 1), 1));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     } else {
       addToBot((AbstractGameAction)new SFXAction("Clarke"));
       addToBot((AbstractGameAction)new GainEnergyAction(5));
       boolean powerExists = false;
       for (AbstractPower pow : abstractPlayer.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           powerExists = true;
           break;
         } 
       } 
       if (powerExists) {
           if (abstractPlayer instanceof  AbstractShadowversePlayer){
               ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
           }
         addToBot((AbstractGameAction)new BlockPerCardAction(this.block));
         addToBot((AbstractGameAction)new ExpertiseAction((AbstractCreature)abstractPlayer, 10));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -this.magicNumber), -this.magicNumber));
       } 
     } 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Clarke();
   }
 }

