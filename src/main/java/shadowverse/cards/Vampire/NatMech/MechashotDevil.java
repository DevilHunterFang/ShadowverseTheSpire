 package shadowverse.cards.Vampire.NatMech;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.tempCards.Miracle;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.cards.Neutral.Status.EvolutionPoint;
 import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.NextTurnProduct;


 public class MechashotDevil
   extends CustomCard
 {
   public static final String ID = "shadowverse:MechashotDevil";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MechashotDevil");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MechashotDevil.png";

   public MechashotDevil() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 6;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.cardsToPreview = (AbstractCard)new ProductMachine();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("MechashotDevil"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     int machineCount = 0;
     int mThisCombat = 0;
     for (AbstractCard c : abstractPlayer.hand.group) {
       if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this) {
         machineCount++;
       }
     } 
     if (machineCount >= 1 && !abstractPlayer.hasPower(NextTurnProduct.POWER_ID)) {
         addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new NextTurnProduct(abstractPlayer)));
     }
     for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
         if (c.type!=CardType.SKILL&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
             mThisCombat++;
         }
     }
     if (mThisCombat>=10){
         addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint()));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle()));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MechashotDevil();
   }
 }

