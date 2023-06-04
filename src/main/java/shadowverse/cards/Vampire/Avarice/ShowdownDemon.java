 package shadowverse.cards.Vampire.Avarice;

 import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DiscardAction;
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
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
 import com.megacrit.cardcrawl.random.Random;
import shadowverse.cards.Neutral.Temp.Jeep;
import shadowverse.cards.Neutral.Temp.Motorbike;
import shadowverse.characters.Vampire;

import java.util.ArrayList;

 public class ShowdownDemon
   extends CustomCard {
   public static final String ID = "shadowverse:ShowdownDemon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShowdownDemon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   private float rotationTimer;
   private int previewIndex;
   public static AbstractCard returnTrulyRandomMysterianInCombat(Random rng) {
     ArrayList<AbstractCard> list = new ArrayList<>();
       Motorbike motorbike = new Motorbike();
       Jeep jeep = new Jeep();
     list.add(motorbike);
     list.add(jeep);
     return list.get(rng.random(list.size() - 1));
   }
   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Motorbike());
     list.add(new Jeep());
     return list;
   }
   public static final String IMG_PATH = "img/cards/ShowdownDemon.png";
   public ShowdownDemon() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.baseBlock = 4;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
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
       addToBot((AbstractGameAction)new SFXAction("ShowdownDemon"));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, 2, false));
     AbstractCard c = returnTrulyRandomMysterianInCombat(AbstractDungeon.cardRandomRng).makeCopy();
     c.setCostForTurn(0);
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DrawCardNextTurnPower(abstractPlayer,2),2));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShowdownDemon();
   }
 }

