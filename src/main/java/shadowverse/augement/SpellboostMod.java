package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

public class SpellboostMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:SpellboostMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return !card.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)
                && (card.type == AbstractCard.CardType.SKILL || card.type == AbstractCard.CardType.ATTACK)
                && card.cost > 0
                && cardCheck(card, (c) -> doesntUpgradeCost());
    }

    @Override
    public String getPrefix() {
        return TEXT[0];
    }

    public String getSuffix() {
        return TEXT[1];
    }

    public void onInitialApplication(AbstractCard card) {
        card.cost += 2;
        card.costForTurn = card.cost;
        if (!card.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
            card.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
        }
    }

    @Override
    public void onOtherCardPlayed(AbstractCard card, AbstractCard otherCard, CardGroup group) {
        if (AbstractDungeon.player.hand.contains(card) && otherCard.type == AbstractCard.CardType.SKILL) {
            card.flash();
            addToBot((AbstractGameAction) new ReduceCostAction(card));
            addToBot((AbstractGameAction) new SFXAction("spell_boost"));
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
        return new SpellboostMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
