 package shadowverse.cards.Neutral.Neutral;

 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
 import shadowverse.action.ChoiceAction;
 import shadowverse.cards.AbstractNeutralCard;
 import shadowverse.cards.Neutral.Temp.DemonOfPurgatory;
 import shadowverse.cards.Neutral.Temp.Desire;
 import shadowverse.cards.Neutral.Temp.HellBeast;

 import java.util.ArrayList;

 public class EmbodimentOfCocytus
   extends AbstractNeutralCard {
   public static final String ID = "shadowverse:EmbodimentOfCocytus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EmbodimentOfCocytus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/EmbodimentOfCocytus.png";

   private float rotationTimer;

   private int previewIndex;

   public static ArrayList<AbstractCard> returnSatanCard(){
       ArrayList<AbstractCard> list = new ArrayList<>();
       list.add(new Desire());
       list.add(new DemonOfPurgatory());
       list.add(new HellBeast());
       return list;
   }
   
   public EmbodimentOfCocytus() {
     super("shadowverse:EmbodimentOfCocytus", NAME, "img/cards/EmbodimentOfCocytus.png", 2, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.SELF);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }

     public void update() {
         super.update();
         if (this.hb.hovered)
             if (this.rotationTimer <= 0.0F) {
                 this.rotationTimer = 2.0F;
                 this.cardsToPreview = (AbstractCard)returnSatanCard().get(previewIndex).makeCopy();
                 if (this.previewIndex == returnSatanCard().size() - 1) {
                     this.previewIndex = 0;
                 } else {
                     this.previewIndex++;
                 }
             } else {
                 this.rotationTimer -= Gdx.graphics.getDeltaTime();
             }
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("EmbodimentOfCocytus"));
             addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
     addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, 6));
     addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[] { (AbstractCard)new Desire(), (AbstractCard)new DemonOfPurgatory(), (AbstractCard)new HellBeast() }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new EmbodimentOfCocytus();
   }
 }

