package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.CardModifierPatches;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.Iterator;

public class ArmedMod extends AbstractCardModifier {
    public static String ID = "shadowverse:ArmedMod";

    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(AbstractShadowversePlayer.Enums.ARMED)){
            card.tags.add(AbstractShadowversePlayer.Enums.ARMED);
            card.rawDescription = CardCrawlGame.languagePack.getUIString("shadowverse:ArmedMod").TEXT[0] + card.rawDescription;
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player,1));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ArmedMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
