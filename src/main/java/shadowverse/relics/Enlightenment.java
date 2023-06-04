package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Neutral.OmenOfOne;

import java.util.ArrayList;


public class Enlightenment
        extends CustomRelic {
    public static final String ID = "shadowverse:Enlightenment";
    public static final String IMG = "img/relics/Enlightenment.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Enlightenment_Outline.png";

    public Enlightenment() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        int amount = 1;
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            tmp.add(c.cardID);
        }
        if (AbstractDungeon.player.masterDeck.group.stream().anyMatch(card -> card instanceof OmenOfOne) || deckCheck) {
            amount = 3;
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BufferPower(AbstractDungeon.player, 1), 1));
        }
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount), amount));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, amount), amount));
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new Enlightenment();
    }
}

