 package shadowverse.cards.Nemesis.Artifact;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Temp.*;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;

 import java.util.ArrayList;


 public class GenesisArtifact
   extends CustomCard
 {
   public static final String ID = "shadowverse:GenesisArtifact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GenesisArtifact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GenesisArtifact.png";
     private float rotationTimer;
     private int previewIndex;
     public static ArrayList<AbstractCard> returnChoice() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new GuardArtifact());
         list.add(new DefectArtifact());
         list.add(new CannonArtifact());
         return list;
     }

   public GenesisArtifact() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 8;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
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

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       upgradeMagicNumber(1);
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot(new GainBlockAction(abstractPlayer,this.block));
       AbstractCard g = new GuardArtifact();
       AbstractCard d = new DefectArtifact();
       g.setCostForTurn(0);
       d.setCostForTurn(0);
       addToBot(new MakeTempCardInHandAction(g));
       addToBot(new MakeTempCardInHandAction(d));
       ArrayList<AbstractCard> list = new ArrayList<>();
       ArrayList<String> dup = new ArrayList<>();
       for (AbstractCard c: abstractPlayer.exhaustPile.group){
           if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)&&!dup.contains(c.cardID)){
               dup.add(c.cardID);
               AbstractCard card = c.makeCopy();
               list.add(card);
           }
       }
       if (list.size()>=6){
           AbstractCard cannon = new CannonArtifact();
           addToBot(new MakeTempCardInHandAction(cannon,this.magicNumber));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return new GenesisArtifact();
   }
 }

