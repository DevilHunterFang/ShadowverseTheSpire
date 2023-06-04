package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.cards.Neutral.Temp.*;

import java.util.ArrayList;

public class SatanaelAction extends AbstractGameAction {

    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Retain").TEXT[0];

    public static ArrayList<AbstractCard> returnSatanDeck() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Flamelord());
        list.add(new Desire());
        list.add(new Scorpion());
        list.add(new HellBeast());
        list.add(new WrathfulIcefiend());
        list.add(new ViciousCommander());
        list.add(new DemonOfPurgatory());
        list.add(new Behemoth());
        list.add(new InfernalGaze());
        list.add(new InfernalSurge());
        list.add(new HeavenFall());
        list.add(new EarthFall());
        list.add(new Servant());
        list.add(new SilentRider());
        list.add(new Dis());
        return list;
    }

    public static AbstractCard returnSatanCard(Random rng) {
        return returnSatanDeck().get(rng.random(returnSatanDeck().size() - 1));
    }

    public void randomSatanCard(){
        ArrayList<AbstractCard> l = new ArrayList<AbstractCard>();
        AbstractCard[] card = new AbstractCard[6];
        ArrayList<String> dup = new ArrayList<>();
        while (true){
            AbstractCard c = returnSatanCard(AbstractDungeon.cardRandomRng).makeCopy();
            if (!dup.contains(c.cardID)){
                l.add(c);
                dup.add(c.cardID);
            }
            if (l.size() >= 6){
                break;
            }
        }
        for (AbstractCard c:l){
            c.retain= true;
            c.selfRetain = true;
            c.rawDescription += " NL "+TEXT+" 。";
            c.initializeDescription();
            c.applyPowers();
            AbstractDungeon.player.hand.addToTop(c);
        }
    }

    @Override
    public void update() {
        randomSatanCard();
        tickDuration();
        this.isDone = true;
    }
}
