 package shadowverse.cards.Elf.Default;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.TextureAtlas;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import shadowverse.characters.Elf;


 public class Cassiopeia extends CustomCard {
   public static final String ID = "shadowverse:Cassiopeia";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cassiopeia");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Cassiopeia.png";
     private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/Cassiopeia_L.png");

   public Cassiopeia() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
     this.baseDamage = 5;
     this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
           addToBot((AbstractGameAction)new SFXAction("Cassiopeia_L"));
       }else {
           addToBot((AbstractGameAction)new SFXAction("Cassiopeia"));
       }
       for (int i = 0; i < abstractPlayer.hand.group.size()-1; i++) {
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Cassiopeia();
   }
 }

