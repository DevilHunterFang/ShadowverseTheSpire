 package shadowverse.cards.Necromancer.Ghosts;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Status.Ghost;
 import shadowverse.cards.Neutral.Status.GiantGhost;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Necromancer;
 import shadowverse.powers.MasqueradeGhostPower;
 import shadowverse.powers.NextTurnMasqueradeGhostPower;

 import java.util.ArrayList;


 public class MasqueradeGhost
   extends CustomCard {
   public static final String ID = "shadowverse:MasqueradeGhost";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MasqueradeGhost");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MasqueradeGhost.png";

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Ghost());
     list.add(new GiantGhost());
     return list;
   }

   public MasqueradeGhost() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 12;
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
       upgradeMagicNumber(1);
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
         addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new NextTurnMasqueradeGhostPower(AbstractDungeon.player)));
     }

     public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot(new SFXAction("MasqueradeGhost"));
     addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new MasqueradeGhostPower(abstractPlayer,this.magicNumber,false)));
     addToBot(new MakeTempCardInHandAction(new Ghost()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MasqueradeGhost();
   }
 }


