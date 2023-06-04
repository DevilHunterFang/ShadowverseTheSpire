 package shadowverse.cards.Elf.Left;


 import basemod.abstracts.CustomCard;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
 import com.megacrit.cardcrawl.powers.*;
 import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
 import rs.lazymankits.interfaces.cards.UpgradeBranch;
 import shadowverse.cards.Neutral.Temp.Fairy;
 import shadowverse.characters.Elf;
 import shadowverse.powers.AmatazPower;

 import java.util.ArrayList;
 import java.util.List;


 public class Amataz extends CustomCard implements BranchableUpgradeCard {
   public static final String ID = "shadowverse:Amataz";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Amataz");
   public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Amataz2");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Amataz.png";
   public static final String IMG_PATH2 = "img/cards/Amataz2.png";

   public Amataz() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.baseBlock = 7;
     this.cardsToPreview = new Fairy();
   }
 
   
   public void upgrade() {
       ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       switch (chosenBranch()){
           case 0:
               addToBot((AbstractGameAction)new SFXAction("Amataz"));
               int count = 0;
               for (AbstractCard c:abstractPlayer.hand.group){
                   if (c instanceof Fairy)
                       count++;
               }
               if (count>=2){
                   addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,3));
                   addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature) abstractPlayer,(AbstractCreature) abstractPlayer,(AbstractPower)new PlatedArmorPower((AbstractCreature)abstractPlayer,2),2));
               }
               if ((count>=4)){
                   addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)abstractPlayer, 1), 1));
                   addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new DexterityPower((AbstractCreature)abstractPlayer, 1), 1));
               }
               if (count>=6){
                   addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer,(AbstractCreature)abstractPlayer,(AbstractPower)new AmatazPower((AbstractCreature)abstractPlayer,this.magicNumber),this.magicNumber));
               }
               break;
           case 1:
               addToBot((AbstractGameAction)new SFXAction("Amataz2"));
               int elfCard = 0;
               addToBot((AbstractGameAction)new MoveCardsAction(abstractPlayer.drawPile,abstractPlayer.discardPile, card -> {
                   return card.color==Elf.Enums.COLOR_GREEN&&card.type==CardType.ATTACK&&!card.hasTag(CardTags.STRIKE);
               },11-abstractPlayer.hand.group.size(),abstractCards -> {
                   for (AbstractCard ca:abstractCards){
                       addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                   }
               }));
               for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
                   if (c.color==Elf.Enums.COLOR_GREEN&&c.type==CardType.ATTACK&&!c.hasTag(CardTags.STRIKE))
                       elfCard++;
               }
               if (elfCard>=10){
                   addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,4));
               }
               if (elfCard>=20){
                   for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
                       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)mo, 3, false), 3, true, AbstractGameAction.AttackEffect.NONE));
                   }
               }
               break;
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Amataz();
   }

     @Override
     public List<UpgradeBranch> possibleBranches() {
         ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Amataz.this.timesUpgraded;
                 Amataz.this.upgraded = true;
                 Amataz.this.name = NAME + "+";
                 Amataz.this.baseBlock = 12;
                 Amataz.this.baseMagicNumber = 5;
                 Amataz.this.magicNumber = Amataz.this.baseMagicNumber;
                 Amataz.this.upgradedBlock= true;
                 Amataz.this.upgradedMagicNumber = true;
             }
         });
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Amataz.this.timesUpgraded;
                 Amataz.this.upgraded = true;
                 Amataz.this.textureImg = IMG_PATH2;
                 Amataz.this.loadCardImage(IMG_PATH2);
                 Amataz.this.name = cardStrings2.NAME;
                 Amataz.this.initializeTitle();
                 Amataz.this.rawDescription = cardStrings2.DESCRIPTION;
                 Amataz.this.initializeDescription();
             }
         });
         return list;
     }
 }

