package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import shadowverse.cards.Neutral.Temp.DeadSoulTaker;
import shadowverse.cards.Bishop.Amulet2.Jatelant;
import shadowverse.cards.Elf.Left.PrimalGigant;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class JatelantAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> tmp = new ArrayList<String>();
    private AbstractPlayer p = AbstractDungeon.player;
    public int[] multiDamage;
    private DamageInfo.DamageType damageType;
    private int goldAmt;


    public JatelantAction(int[] multiDamage,DamageInfo.DamageType damageType,int goldAmt){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.goldAmt = goldAmt;
    }


    @Override
    public void update() {
        if (p instanceof AbstractShadowversePlayer){
            int count = ((AbstractShadowversePlayer) p).amuletCount;
            int goldCount = (count+2)/3>5?5:(count+2)/3;
            int dmgCount = (count+1)/3>5?5:(count+1)/3;
            int cardCount = count/3>4?4:count/3;
            if (goldCount>0){
                addToBot((AbstractGameAction)new GainGoldAction(goldCount*goldAmt));
            }
            if (dmgCount>0){
                addToBot((AbstractGameAction)new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
                for (int i = 0; i < dmgCount; i++){
                    addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05F));
                    addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)this.p, this.multiDamage, this.damageType, AttackEffect.FIRE, true));
                }
                addToBot((AbstractGameAction)new HealAction(p,p,3*goldCount));
            }
            if (cardCount>0){
                for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
                    if (c.type == AbstractCard.CardType.ATTACK){
                        if (tmp.contains(c.cardID)||c instanceof PrimalGigant||c instanceof DeadSoulTaker || c instanceof Jatelant) {
                            continue;
                        }
                        tmp.add(c.cardID);
                        list.add(c);
                    }
                }
                int toAdd = cardCount;
                Collections.shuffle(list);
                for (int i =0;i<toAdd;i++){
                    if (list.size()>i){
                        AbstractCard tempCard = list.get(i).makeSameInstanceOf();
                        if (tempCard.costForTurn > 0) {
                            tempCard.cost = 0;
                            tempCard.costForTurn = 0;
                            tempCard.isCostModifiedForTurn = true;
                        }
                        tempCard.exhaustOnUseOnce = true;
                        tempCard.exhaust = true;
                        tempCard.applyPowers();
                        tempCard.lighten(true);
                        tempCard.setAngle(0.0F);
                        tempCard.drawScale = 0.12F;
                        tempCard.targetDrawScale = 0.75F;
                        tempCard.current_x = Settings.WIDTH / 2.0F;
                        tempCard.current_y = Settings.HEIGHT / 2.0F;
                        p.hand.addToTop(tempCard);
                    }
                }
                p.hand.refreshHandLayout();
                p.hand.applyPowers();
            }
        }
        this.isDone = true;
    }
}
