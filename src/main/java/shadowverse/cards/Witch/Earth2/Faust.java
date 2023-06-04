 package shadowverse.cards.Witch.Earth2;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
 import shadowverse.powers.FaustPower;
 import shadowverse.powers.FaustPower2;

 import java.util.ArrayList;
import java.util.List;

 public class Faust
   extends CustomCard implements BranchableUpgradeCard {
   public static final String ID = "shadowverse:Faust";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Faust");
     public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Faust2");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Faust.png";
     public static final String IMG_PATH2 = "img/cards/Faust2.png";
   
   public Faust() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
       ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       switch (chosenBranch()){
           case 0:
               addToBot((AbstractGameAction)new SFXAction("Faust"));
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new FaustPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
               break;
           case 1:
               addToBot((AbstractGameAction)new SFXAction("Faust2"));
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new FaustPower2((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
               break;
           default:
               break;
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Faust();
   }

     @Override
     public List<UpgradeBranch> possibleBranches() {
         ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Faust.this.timesUpgraded;
                 Faust.this.upgraded = true;
                 Faust.this.name = NAME + "+";
                 Faust.this.initializeTitle();
                 Faust.this.upgradeName();
                 Faust.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                 initializeDescription();
                 Faust.this.baseMagicNumber = 2;
                 Faust.this.magicNumber = Faust.this.baseMagicNumber;
                 Faust.this.upgradedMagicNumber = true;
                 Faust.this.isEthereal = false;
             }
         });
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Faust.this.timesUpgraded;
                 Faust.this.upgraded = true;
                 Faust.this.textureImg = IMG_PATH2;
                 Faust.this.loadCardImage(IMG_PATH2);
                 Faust.this.name = cardStrings2.NAME;
                 Faust.this.initializeTitle();
                 Faust.this.rawDescription = cardStrings2.DESCRIPTION;
                 Faust.this.initializeDescription();
                 Faust.this.baseMagicNumber = 20;
                 Faust.this.magicNumber = Faust.this.baseMagicNumber;
                 Faust.this.upgradedMagicNumber = true;
                 Faust.this.rarity = CardRarity.UNCOMMON;
                 Faust.this.setDisplayRarity(Faust.this.rarity);
                 Faust.this.isEthereal = true;
             }
         });
         return list;
     }
 }

