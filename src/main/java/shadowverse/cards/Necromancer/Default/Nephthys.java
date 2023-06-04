 package shadowverse.cards.Necromancer.Default;


 import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.action.NephthysAction;
import shadowverse.characters.Necromancer;


 public class Nephthys extends CustomCard {
   public static final String ID = "shadowverse:Nephthys";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nephthys");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Nephthys.png";

   public Nephthys() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 10;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot(new SFXAction("Nephthys"));
       addToBot(new GainBlockAction(abstractPlayer,this.block));
       addToBot(new VFXAction(new BorderFlashEffect(Color.PURPLE, true),1.0f));
       addToBot(new NephthysAction());
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Nephthys();
   }
 }

