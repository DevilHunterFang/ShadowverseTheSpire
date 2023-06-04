 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.ShikigamiSummoning;


 public class KuonWeapon
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KuonWeapon";
   public static final String IMG = "img/relics/KuonWeapon.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KuonWeapon_Outline.png";

   public KuonWeapon() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
       AbstractCard c = (AbstractCard)new ShikigamiSummoning();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
   }

   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new KuonWeapon();
   }
 }

