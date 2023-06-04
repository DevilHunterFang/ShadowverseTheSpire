package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.GildedBlade;
import shadowverse.cards.Neutral.Temp.GildedBoots;
import shadowverse.cards.Neutral.Temp.GildedGoblet;
import shadowverse.cards.Neutral.Temp.GildedNecklace;

import java.util.ArrayList;

public class Lagedor
        extends CustomRelic {
    public static final String ID = "shadowverse:Lagedor";
    public static final String IMG = "img/relics/Lagedor.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Lagedor_Outline.png";

    public Lagedor() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        return list;
    }


    public void atTurnStart() {
        if (!this.grayscale)
            this.counter++;
        if (this.counter < 3) {
            int r1 = AbstractDungeon.cardRandomRng.random(3);
            AbstractCard c1 = returnProphecy().get(r1);
            addToBot(new MakeTempCardInHandAction(c1));
        }
        if (this.counter == 3) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 14));
            this.counter = -1;
            this.grayscale = true;
        }
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new Lagedor();
    }
}
