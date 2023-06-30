package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.action.BurialAction;
import shadowverse.powers.MementoPower;

public class BurialMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:BurialMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return card.cost != -2
                && (card.type == AbstractCard.CardType.ATTACK);
    }

    @Override
    public String getPrefix() {
        return TEXT[0];
    }

    @Override
    public String getSuffix() {
        return TEXT[1];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractPlayer abstractPlayer = AbstractDungeon.player;
        addToBot(new BurialAction(1, new DrawCardAction(1)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != card && c.type == AbstractCard.CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID))
                addToBot(new DrawCardAction(1));
        }
    }

    public String getAugmentDescription() {
        return TEXT[2];
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return insertAfterText(rawDescription, CARD_TEXT[0]);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BurialMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
