 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Witch.Default.ConjureTwosome;
import shadowverse.cards.Witch.Default.Petrification;
import shadowverse.cards.Witch.Default.StaffOfWhirlwinds;

import java.util.ArrayList;
 
 
 
 
 
 
 public class Eleanor
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Eleanor";
   public static final String IMG = "img/relics/Eleanor.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Eleanor_Outline.png";
   
   private AbstractCard generateCard(Random rng) {
     ArrayList<AbstractCard> list = new ArrayList<>();
     StaffOfWhirlwinds staffOfWhirlwinds = new StaffOfWhirlwinds();
     Petrification petrification = new Petrification();
     ConjureTwosome conjureTwosome = new ConjureTwosome();
     list.add(staffOfWhirlwinds);
     list.add(petrification);
     list.add(conjureTwosome);
     return list.get(rng.random(list.size() - 1));
   }
   
   public Eleanor() {
     super("shadowverse:Eleanor", ImageMaster.loadImage("img/relics/Eleanor.png"), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     this.counter = 0;
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
   }
   
   public void atTurnStart() {
     if (!this.grayscale) {
       this.counter++;
       CardCrawlGame.sound.playA("Eleanor", 0.0F);
       AbstractCard tmp = generateCard(AbstractDungeon.cardRandomRng).makeCopy();
       tmp.setCostForTurn(0);
       tmp.exhaustOnUseOnce = true;
       tmp.exhaust = true;
       tmp.initializeDescription();
       tmp.applyPowers();
       AbstractDungeon.player.hand.addToTop(tmp);
     } 
     if (this.counter == 3) {
       flash();
       this.counter = -1;
       this.grayscale = true;
     } 
   }
 
   
   public void onVictory() {
     this.counter = -1;
     this.grayscale = false;
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Eleanor();
   }
 }
