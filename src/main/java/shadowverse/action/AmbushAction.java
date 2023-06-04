package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.Ambush;
import shadowverse.cards.Neutral.Temp.AmbushAttack;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.Minion;
import shadowverse.powers.DualbladePower;

import java.util.ArrayList;

public class AmbushAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:Ambush")).TEXT;

    private AbstractPlayer p = AbstractDungeon.player;
    private boolean retrieveCard = false;
    private AmbushMinion orb;

    public AmbushAction(AmbushMinion orb){
        this.orb = orb;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), TEXT[0], false);
            tickDuration();
            return;
        }
        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard;
                if (disCard instanceof Ambush){
                    AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(1, 0, (Minion) orb));
                    if (p.hasPower(DualbladePower.POWER_ID)){
                        AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(1, 0, (Minion) orb));;
                    }
                }else {
                    orb.ambush = false;
                    if (orb.defense>0){
                        orb.effect();
                        AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, orb));
                        if (p.hasPower(DualbladePower.POWER_ID)&&orb.defense>1){
                            orb.effect();
                            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, orb));
                        }
                        orb.updateDescription();
                    }
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices()  {
        ArrayList<AbstractCard> derp = new ArrayList<>();
        derp.add(new Ambush());
        derp.add(new AmbushAttack());
        return derp;
    }
}
