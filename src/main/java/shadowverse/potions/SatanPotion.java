package shadowverse.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.*;

import java.util.ArrayList;

public class SatanPotion extends CustomPotion {
    public static final String POTION_ID = "shadowverse:SatanPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("shadowverse:SatanPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static ArrayList<AbstractCard> returnCocytusDeck() {
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

    public static AbstractCard returnCocytusCard(Random rng) {
        return returnCocytusDeck().get(rng.random(returnCocytusDeck().size() - 1));
    }

    public SatanPotion() {
        super(NAME, POTION_ID, AbstractPotion.PotionRarity.RARE, AbstractPotion.PotionSize.GHOST, AbstractPotion.PotionColor.FEAR);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[0];
        } else {
            this.description = potionStrings.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        ChoiceSatanCard();
        if (this.potency>1)
            ChoiceSatanCard();
    }

    public  void ChoiceSatanCard(){
        ArrayList<AbstractCard> l = new ArrayList<AbstractCard>();
        AbstractCard[] card = new AbstractCard[3];
        ArrayList<String> dup = new ArrayList<>();
        while (true){
            AbstractCard c = returnCocytusCard(AbstractDungeon.cardRandomRng).makeCopy();
            if (!dup.contains(c.cardID)){
                l.add(c);
                dup.add(c.cardID);
            }
            if (l.size() >= 3){
                break;
            }
        }
        card[0] = l.get(0);
        card[1] = l.get(1);
        card[2] = l.get(2);
        addToBot((AbstractGameAction)new ChoiceAction(card));
    }

    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SatanPotion();
    }
}
