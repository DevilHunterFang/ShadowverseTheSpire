 package shadowverse.cards.Necromancer.Default;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Necromancer.Necromancy.WightKing;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;


 public class ImmortalThane
   extends CustomCard {
   public static final String ID = "shadowverse:ImmortalThane";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImmortalThane");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImmortalThane.png";
     private float rotationTimer;
     private int previewIndex;

     public static ArrayList<AbstractCard> returnShikigami() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new Wight());
         list.add(new WightKing());
         return list;
     }

   public ImmortalThane() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.NONE);
     this.baseBlock = 8;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
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
                 this.cardsToPreview = (AbstractCard)returnShikigami().get(previewIndex).makeCopy();
                 if (this.previewIndex == returnShikigami().size() - 1) {
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

     @Override
     public void triggerOnExhaust() {
         AbstractCard c = (AbstractCard)new WightKing();
         c.setCostForTurn(0);
         if (this.upgraded){
             c.upgrade();
         }
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ImmortalThane"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     AbstractCard c = (AbstractCard)new Wight();
     c.setCostForTurn(0);
       if (this.upgraded){
           c.upgrade();
       }
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,2));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ImmortalThane();
   }
 }

