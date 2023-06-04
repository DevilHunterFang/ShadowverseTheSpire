 package shadowverse.cards.Neutral.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class ProtectArtifact
   extends CustomCard
 {
   public static final String ID = "shadowverse:ProtectArtifact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ProtectArtifact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ProtectArtifact.png";

   public ProtectArtifact() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 24;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ProtectArtifact();
   }
 }

