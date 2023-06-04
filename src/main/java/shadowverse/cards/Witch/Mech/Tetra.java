 package shadowverse.cards.Witch.Mech;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.ArtifactPower;
 import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.RepairMode;
import shadowverse.cards.Neutral.Temp.SonicFour;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
 import shadowverse.powers.SonicFourPower2;

 import java.util.ArrayList;
import java.util.List;


 public class Tetra
   extends CustomCard implements BranchableUpgradeCard
 {
   public static final String ID = "shadowverse:Tetra";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tetra");
   public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tetra2");
   public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tetra3");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Tetra.png";
     public static final String IMG_PATH3 = "img/cards/Tetra3.png";
   
   public Tetra() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.cardsToPreview = (AbstractCard)new SonicFour();
   }
 
   
   public void upgrade() {
       ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       switch (chosenBranch()){
           case 0:
               addToBot((AbstractGameAction)new SFXAction("Tetra"));
               addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
               addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new RepairMode(), 1));
               break;
           case 1:
               addToBot((AbstractGameAction)new SFXAction("Tetra2"));
               if (!abstractPlayer.hasPower(SonicFourPower2.POWER_ID))
                   addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new SonicFourPower2(abstractPlayer)));
               addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new ArtifactPower(abstractPlayer,1),1));
               break;
           case 2:
               addToBot((AbstractGameAction)new SFXAction("Tetra3"));
               addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 2));
               addToBot((AbstractGameAction)new GainEnergyAction(1));
               break;
           default:
               break;
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Tetra();
   }

     @Override
     public List<UpgradeBranch> possibleBranches() {
         ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Tetra.this.timesUpgraded;
                 Tetra.this.upgraded = true;
                 Tetra.this.name = NAME + "+";
                 Tetra.this.initializeTitle();
                 Tetra.this.baseBlock = 10;
                 Tetra.this.upgradedBlock = true;
                 Tetra.this.cardsToPreview.upgrade();
                 Tetra.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                 Tetra.this.initializeDescription();
             }
         });
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Tetra.this.timesUpgraded;
                 Tetra.this.upgraded = true;
                 Tetra.this.name = cardStrings2.NAME;
                 Tetra.this.initializeTitle();
                 Tetra.this.rawDescription = cardStrings2.DESCRIPTION;
                 Tetra.this.initializeDescription();
                 Tetra.this.baseBlock = 12;
                 Tetra.this.upgradedBlock = true;
                 Tetra.this.cardsToPreview = (AbstractCard)new SonicFour();
             }
         });
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Tetra.this.timesUpgraded;
                 Tetra.this.upgraded = true;
                 Tetra.this.textureImg = IMG_PATH3;
                 Tetra.this.loadCardImage(IMG_PATH3);
                 Tetra.this.name = cardStrings3.NAME;
                 Tetra.this.initializeTitle();
                 Tetra.this.rawDescription = cardStrings3.DESCRIPTION;
                 Tetra.this.initializeDescription();
                 Tetra.this.baseBlock = 6;
                 Tetra.this.upgradedBlock = true;
                 Tetra.this.cardsToPreview = (AbstractCard)new SonicFour();
             }
         });
         return list;
     }
 }


