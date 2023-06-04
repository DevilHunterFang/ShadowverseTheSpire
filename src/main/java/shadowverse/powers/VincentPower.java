 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import shadowverse.cards.Neutral.Temp.JudgmentWord;

import java.util.ArrayList;


 public class VincentPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:VincentPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:VincentPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public ArrayList<String> skillCount = new ArrayList<String>();
   
   public VincentPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = NeutralPowertypePatch.NEUTRAL;
     updateDescription();
     this.img = new Texture("img/powers/VincentPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
       if (card.type == AbstractCard.CardType.SKILL){
           skillCount.add(card.cardID);
           int i = 0;
           for (String s : skillCount){
               if (s.equals(card.cardID)){
                   i++;
               }
           }
           if (i%3==0 && i<=18){
               addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIRLWIND"));
               addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(), 0.0F));
               addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(12, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
           }
       }
   }

   @Override
   public void atStartOfTurnPostDraw() {
       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new JudgmentWord(),1));
   }
 }
