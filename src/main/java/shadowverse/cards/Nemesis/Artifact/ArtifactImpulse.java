 package shadowverse.cards.Nemesis.Artifact;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Neutral.Temp.AncientArtifact;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;

 import java.util.ArrayList;


 public class ArtifactImpulse
   extends CustomCard
 {
   public static final String ID = "shadowverse:ArtifactImpulse";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArtifactImpulse");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ArtifactImpulse.png";

   public ArtifactImpulse() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ALL);
     this.baseDamage = 24;
     this.cardsToPreview = (AbstractCard)new AncientArtifact();
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
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
           addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
       }else {
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ArtifactImpulse();
   }
 }

