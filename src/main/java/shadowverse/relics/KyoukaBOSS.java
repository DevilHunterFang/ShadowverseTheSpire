 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardQueueItem;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.patch.CharacterSelectScreenPatches;


 public class KyoukaBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KyoukaBOSS";
   public static final String IMG = "img/relics/KyoukaBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KyoukaBOSS_Outline.png";


   public KyoukaBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onPlayCard(AbstractCard c, AbstractMonster m) {
         if (c.type== AbstractCard.CardType.SKILL){
             this.counter++;
             if (this.counter == 7) {
                 this.counter = 0;
                 flash();
                 AbstractCard tmp = c.makeSameInstanceOf();
                 AbstractDungeon.player.limbo.addToBottom(tmp);
                 tmp.current_x = c.current_x;
                 tmp.current_y = c.current_y;
                 tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                 tmp.target_y = (float)Settings.HEIGHT / 2.0F;
                 if (m != null) {
                     tmp.calculateCardDamage(m);
                 }
                 tmp.purgeOnUse = true;
                 AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, c.energyOnUse, true, true), true);
                 this.pulse = false;
             } else if (this.counter == 6) {
                 beginPulse();
                 this.pulse = true;
                 AbstractDungeon.player.hand.refreshHandLayout();
                 addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
             }
         }
     }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive.ID)
                 && ((CharacterSelectScreenPatches.characters[0]).reskinCount == 0||
                 (CharacterSelectScreenPatches.characters[0]).reskinCount == 3);
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new KyoukaBOSS();
   }
 }

