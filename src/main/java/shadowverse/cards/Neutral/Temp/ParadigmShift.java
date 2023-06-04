 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;

 import java.util.ArrayList;


 public class ParadigmShift
   extends CustomCard
 {
   public static final String ID = "shadowverse:ParadigmShift";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ParadigmShift");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ParadigmShift.png";
   private float rotationTimer;
   private int previewIndex;
   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new ProtectArtifact());
     list.add(new EdgeArtifact());
     list.add(new BlitzArtifact());
     return list;
   }

   public ParadigmShift() {
     super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
     this.selfRetain = true;
     this.exhaust = true;
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

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)) {
       flash();
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     }
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard p = (AbstractCard)new ProtectArtifact();
     AbstractCard e = (AbstractCard)new EdgeArtifact();
     AbstractCard b = (AbstractCard)new BlitzArtifact();
     if (this.upgraded){
       p.upgrade();
       e.upgrade();
       b.upgrade();
     }
     addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[] { p,e,b}));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ParadigmShift();
   }
 }

