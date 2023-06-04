 package shadowverse.cards.Vampire.Basic;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;


 public class DarkGeneral extends CustomCard {
   public static final String ID = "shadowverse:DarkGeneral";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DarkGeneral");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DarkGeneral.png";
   private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/DarkGeneral_L.png");

   public DarkGeneral() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.BASIC, CardTarget.ENEMY);
     this.baseDamage = 10;
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
       addToBot((AbstractGameAction)new SFXAction("DarkGeneral_L"));
     }else {
       addToBot((AbstractGameAction)new SFXAction("DarkGeneral"));
     }
     if (abstractPlayer.stance.ID.equals(Vengeance.STANCE_ID)||abstractPlayer.hasPower(EpitaphPower.POWER_ID)){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     }else
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DarkGeneral();
   }
 }

