 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.cards.Neutral.Temp.WhiteArtifact;

 public class BlackRelic
   extends CustomRelic {
   public static final String ID = "shadowverse:BlackRelic";
   public static final String IMG = "img/relics/BlackRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/BlackRelic_Outline.png";

   public BlackRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onExhaust(AbstractCard card) {
         if (card.type== AbstractCard.CardType.ATTACK)
             if (!(AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                 AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                 addToBot((AbstractGameAction)new ApplyPowerAction(randomMonster,AbstractDungeon.player,(AbstractPower)new VulnerablePower(randomMonster,1,false),1));
             }
     }

     @Override
     public void atBattleStart(){
       if (AbstractDungeon.player.hasRelic(WhiteRelic.ID)){
           addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
           addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new WhiteArtifact()));
       }
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new BlackRelic();
   }
 }
