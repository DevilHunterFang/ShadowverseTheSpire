 package shadowverse.cards.Dragon.Discard1;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ImperialDragoonAction;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


 public class ImperialDragoon extends CustomCard {
   public static final String ID = "shadowverse:ImperialDragoon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImperialDragoon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImperialDragoon.png";
   private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/ImperialDragoon_L.png");

   public ImperialDragoon() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ALL_ENEMY);
     this.baseDamage = 12;
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
       addToBot(new SFXAction("ImperialDragoon_L"));
     }else {
       addToBot(new SFXAction("ImperialDragoon"));
     }
     addToBot(new ImperialDragoonAction(this.damage));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ImperialDragoon();
   }
 }

