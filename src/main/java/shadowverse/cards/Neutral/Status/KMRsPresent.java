 package shadowverse.cards.Neutral.Status;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.DeadSoulTaker;
import shadowverse.cards.Neutral.Temp.ImperialSaint;
import shadowverse.cards.Neutral.Temp.*;

import java.util.ArrayList;


 public class KMRsPresent
   extends CustomCard
 {
   public static final String ID = "shadowverse:KMRsPresent";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:KMRsPresent");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/KMRsPresent.png";
   private float rotationTimer;
   private int previewIndex;
   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new UltimateBahmut());
     list.add(new ShadowBahmut());
     list.add(new ShiningValkyrie());
     list.add(new LegendSwordCommander());
     list.add(new SevensForceSorcerer());
     list.add(new DeadSoulTaker());
     list.add(new AbyssDoomLord());
     list.add(new ImperialSaint());
     list.add(new SummonDivineTreasure());
     return list;
   }


   public KMRsPresent() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
     this.exhaust = true;
     this.selfRetain = true;
     this.cardsToPreview = new ShadowBahmut();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
     } 
   }

   public void update() {
     super.update();
     if (this.hb.hovered)
       if (this.rotationTimer <= 0.0F) {
         this.rotationTimer = 2.0F;
         this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
         if (this.previewIndex == returnChoice().size() - 1) {
           this.previewIndex = 0;
         } else {
           this.previewIndex++;
         }
       } else {
         this.rotationTimer -= Gdx.graphics.getDeltaTime();
       }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard[] list = returnChoice().toArray(new AbstractCard[returnChoice().size()]);
     addToBot((AbstractGameAction)new ChoiceAction(list));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new KMRsPresent();
   }
 }

