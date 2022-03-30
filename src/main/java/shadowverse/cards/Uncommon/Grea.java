 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.Ember;
import shadowverse.cards.Temp.Inferno;
import shadowverse.cards.Temp.NewEmber;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;
import java.util.List;


 public class Grea
   extends CustomCard implements BranchableUpgradeCard {
   public static final String ID = "shadowverse:Grea";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Grea");
   public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewGrea");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Grea.png";
   public static final String IMG_PATH2 = "img/cards/NewGrea.png";

   private float rotationTimer;
   private int previewIndex;
   private boolean branch2 = false;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Ember());
     list.add(new Inferno());
     return list;
   }

   public Grea() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 12;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
   }

   public void update() {
     super.update();
     if (!branch2){
       if (this.hb.hovered)
         if (this.rotationTimer <= 0.0F) {
           this.rotationTimer = 2.0F;
           this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
           if (this.previewIndex == returnChoice().size() - 1) {
             this.previewIndex = 0;
           } else {
             this.previewIndex++;
           }
           if (this.upgraded)
             this.cardsToPreview.upgrade();
         } else {
           this.rotationTimer -= Gdx.graphics.getDeltaTime();
         }
     }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     switch (chosenBranch()){
       case 0:
         AbstractCard a = (AbstractCard)new Ember();
         AbstractCard b = (AbstractCard)new Inferno();
         addToBot((AbstractGameAction)new SFXAction("Grea"));
         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
         if (this.upgraded){
           a.upgrade();
           b.upgrade();
         }
         addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { a, b }));
         addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 1));
         break;
       case 1:
         int drawPileAmt = abstractPlayer.drawPile.group.size();
         int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
         if (drawPileAmt <= masterDeckAmt/2){
           this.damage *=2;
           this.block *=2;
         }
         if (drawPileAmt>=2){
           int rand = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           int rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           while (true){
             if (rand2==rand){
               rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
             }else {
               break;
             }
           }
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand),abstractPlayer.drawPile));
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand2),abstractPlayer.drawPile));
         }
         addToBot((AbstractGameAction)new SFXAction("NewGrea"));
         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),1));
         addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 2));
         break;
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Grea();
   }

   @Override
   public List<UpgradeBranch> possibleBranches() {
     ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Grea.this.timesUpgraded;
         Grea.this.upgraded = true;
         Grea.this.name = NAME + "+";
         Grea.this.initializeTitle();
         Grea.this.baseBlock = 15;
         Grea.this.upgradedBlock = true;
         Grea.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         Grea.this.initializeDescription();
       }
     });
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Grea.this.timesUpgraded;
         Grea.this.upgraded = true;
         Grea.this.textureImg = IMG_PATH2;
         Grea.this.loadCardImage(IMG_PATH2);
         Grea.this.name = cardStrings2.NAME;
         Grea.this.baseDamage = 7;
         Grea.this.upgradedDamage = true;
         Grea.this.initializeTitle();
         Grea.this.rawDescription = cardStrings2.DESCRIPTION;
         Grea.this.initializeDescription();
         Grea.this.cardsToPreview = (AbstractCard)new NewEmber();
         Grea.this.target = CardTarget.ENEMY;
         Grea.this.branch2 = true;
       }
     });
     return list;
   }
 }


