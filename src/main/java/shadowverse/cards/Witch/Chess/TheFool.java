 package shadowverse.cards.Witch.Chess;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;


 public class TheFool
   extends CustomCard {
   public static final String ID = "shadowverse:TheFool";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheFool");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TheFool.png";

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Omniscient());
     list.add(new Ignorant());
     return list;
   }
   
   public TheFool() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.NONE);
     this.exhaust = true;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(0);
     } 
   }

   public void update() {
     super.update();
     if (this.hb.hovered)
       if (this.rotationTimer <= 0.0F) {
         this.rotationTimer = 2.0F;
         this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex);
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
     addToBot((AbstractGameAction)new SFXAction("TheFool"));
     addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { (AbstractCard)new Omniscient(), (AbstractCard)new Ignorant() }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TheFool();
   }
 }


