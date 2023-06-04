 package shadowverse.cards.Nemesis.Tokens;

 import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

 public class HeartlessBattle
   extends CustomCard {
   public static final String ID = "shadowverse:HeartlessBattle";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HeartlessBattle");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HeartlessBattle.png";
     private float rotationTimer;
     private int previewIndex;
     public static ArrayList<AbstractCard> returnChoice() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new Roid());
         list.add(new Victoria());
         list.add(new Puppet());
         return list;
     }

   public HeartlessBattle() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 6;
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
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
                 if (this.upgraded)
                     this.cardsToPreview.upgrade();
             } else {
                 this.rotationTimer -= Gdx.graphics.getDeltaTime();
             }
     }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
         AbstractCard r = (AbstractCard)new Roid();
         AbstractCard v = (AbstractCard)new Victoria();
         AbstractCard p = (AbstractCard)new Puppet();
         if (this.upgraded){
             r.upgrade();
             v.upgrade();
             p.upgrade();
         }
         addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[] { r, v }));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(p));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HeartlessBattle();
   }
 }

