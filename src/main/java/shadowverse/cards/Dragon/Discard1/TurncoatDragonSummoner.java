 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.AzureDragon;
import shadowverse.cards.Neutral.Temp.CrimsonDragon;
import shadowverse.characters.Dragon;

import java.util.ArrayList;


 public class TurncoatDragonSummoner extends CustomCard {
   public static final String ID = "shadowverse:TurncoatDragonSummoner";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TurncoatDragonSummoner");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TurncoatDragonSummoner.png";
   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new CrimsonDragon());
     list.add(new AzureDragon());
     return list;
   }
   
   public TurncoatDragonSummoner() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 8;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }

   @Override
   public void triggerOnManualDiscard() {
     AbstractCard c = new CrimsonDragon();
     if (upgraded)
       c.upgrade();
     addToBot(new MakeTempCardInHandAction(c));
   }

   public void update() {
     super.update();
     if (this.hb.hovered)
       if (this.rotationTimer <= 0.0F) {
         this.rotationTimer = 2.0F;
         this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
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

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("TurncoatDragonSummoner"));
     addToBot(new GainBlockAction(abstractPlayer,this.block));
     AbstractCard c = new AzureDragon();
     if (upgraded)
       c.upgrade();
     addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,false,false));
     addToBot(new MakeTempCardInHandAction(c));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TurncoatDragonSummoner();
   }
 }

