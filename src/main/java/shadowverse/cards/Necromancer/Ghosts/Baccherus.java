 package shadowverse.cards.Necromancer.Ghosts;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Status.Ghost;
 import shadowverse.cards.Neutral.Status.GiantGhost;
 import shadowverse.cards.Neutral.Temp.Baccherus_Copy;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Necromancer;

 import java.util.ArrayList;


 public class Baccherus
   extends CustomCard {
   public static final String ID = "shadowverse:Baccherus";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Baccherus");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Baccherus.png";

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Ghost());
     list.add(new GiantGhost());
     return list;
   }

   public Baccherus() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 2;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
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

     @Override
     public void triggerOnExhaust() {
         addToBot(new MakeTempCardInHandAction(new Baccherus_Copy()));
     }

     public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("Baccherus"));
     for (AbstractCard c:abstractPlayer.hand.group){
         if (c instanceof GiantGhost){
             addToBot(new DrawCardAction(1));
             break;
         }
     }
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     addToBot(new MakeTempCardInHandAction(new Ghost()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Baccherus();
   }
 }


