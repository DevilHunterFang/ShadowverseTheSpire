 package shadowverse.cards.Witch.Spellboost2;
 import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
 import shadowverse.action.ChoiceAction2;
 import shadowverse.action.RunieAction;
 import shadowverse.cards.Neutral.Temp.ProphecyOfDoom;
import shadowverse.cards.Neutral.Temp.ProphecyOfBoons;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;
import java.util.List;

 public class Runie extends CustomCard implements BranchableUpgradeCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Runie");
   public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Runie2");
   public static final String ID = "shadowverse:Runie";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Runie.png";
   public static final String IMG_PATH2 = "img/cards/Runie2.png";
   private boolean branch2 = false;
   private float rotationTimer;
   private int previewIndex;
   public static ArrayList<AbstractCard> returnProphecy(){
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new ProphecyOfBoons());
     list.add(new ProphecyOfDoom());
     return list;
   }

   public Runie() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ALL_ENEMY);
     this.baseDamage = 15;
     this.baseBlock = 9;
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
   }

   public void update(){
     super.update();
     if (branch2){
       if (this.hb.hovered)
         if (this.rotationTimer <= 0.0F) {
           this.rotationTimer = 2.0F;
           this.cardsToPreview = (AbstractCard)returnProphecy().get(previewIndex).makeCopy();
           if (this.previewIndex == returnProphecy().size() - 1) {
             this.previewIndex = 0;
           } else {
             this.previewIndex++;
           }
         } else {
           this.rotationTimer -= Gdx.graphics.getDeltaTime();
         }
     }
   }

   public void upgrade() {
     ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (chosenBranch()!=1){
       if (c.type == CardType.SKILL) {
         flash();
         this.magicNumber = ++this.baseMagicNumber;
         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       }
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     switch (chosenBranch()){
       case 0:
         addToBot((AbstractGameAction)new SFXAction("Runie"));
         addToBot((AbstractGameAction)new RunieAction(this.magicNumber,(AbstractCard)this));
         this.baseMagicNumber = 0;
         this.magicNumber = this.baseMagicNumber;
         break;
       case 1:
         //增幅
         addToBot((AbstractGameAction)new SFXAction("Runie2"));
         for (AbstractCard c : abstractPlayer.hand.group) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
             for (int i = 0; i < this.magicNumber; i++) {
               c.flash();
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
               addToBot((AbstractGameAction)new ReduceCostAction(c));
             }  continue;
           }
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
             for (int i = 0; i < this.magicNumber; i++) {
               c.flash();
               c.magicNumber = ++c.baseMagicNumber;
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
             }
           }
         }

         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
         AbstractCard boons = (AbstractCard)new ProphecyOfBoons();
         AbstractCard doom = (AbstractCard)new ProphecyOfDoom();
         addToBot((AbstractGameAction)new ChoiceAction2(boons,doom));
         break;
       default:
         break;
     }
   }


   public void applyPowers() {
     super.applyPowers();
     if (chosenBranch()!=1){
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.magicNumber;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
     }
   }



   public AbstractCard makeCopy() {
     return (AbstractCard)new Runie();
   }

   @Override
   public List<UpgradeBranch> possibleBranches() {
     ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Runie.this.timesUpgraded;
         Runie.this.upgraded = true;
         Runie.this.name = NAME + "+";
         Runie.this.initializeTitle();
         Runie.this.baseBlock = 12;
         Runie.this.upgradedBlock = true;
         Runie.this.baseDamage = 18;;
         Runie.this.upgradedDamage = true;
       }
     });
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Runie.this.timesUpgraded;
         Runie.this.upgraded = true;
         Runie.this.textureImg = IMG_PATH2;
         Runie.this.loadCardImage(IMG_PATH2);
         Runie.this.name = cardStrings2.NAME;
         Runie.this.initializeTitle();
         Runie.this.rawDescription = cardStrings2.DESCRIPTION;
         Runie.this.initializeDescription();
         Runie.this.baseBlock = 12;
         Runie.this.upgradedBlock = true;
         Runie.this.baseMagicNumber = 1;
         Runie.this.magicNumber = Runie.this.baseMagicNumber;
         Runie.this.upgradedMagicNumber = true;
         Runie.this.tags.remove(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
         Runie.this.exhaust = false;
         Runie.this.branch2 = true;
       }
     });

     return list;
   }
 }


