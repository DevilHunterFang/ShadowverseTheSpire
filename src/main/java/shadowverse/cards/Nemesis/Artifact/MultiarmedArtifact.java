 package shadowverse.cards.Nemesis.Artifact;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;

 import java.util.ArrayList;


 public class MultiarmedArtifact
   extends CustomCard
 {
   public static final String ID = "shadowverse:MultiarmedArtifact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MultiarmedArtifact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MultiarmedArtifact.png";

   public MultiarmedArtifact() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
     this.baseDamage = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
       upgradeDamage(4);
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
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
           addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
           addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MultiarmedArtifact();
   }
 }

