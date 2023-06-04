package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.Skeleton;
import shadowverse.characters.AbstractShadowversePlayer;

public class PrinceCatacombMod extends AbstractCardModifier {
    public static String ID = "shadowverse:PrinceCatacombMod";

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return  rawDescription + CardCrawlGame.languagePack.getUIString("shadowverse:PrinceCatacombMod").TEXT[0];
    }

    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(AbstractShadowversePlayer.Enums.LASTWORD)){
            card.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    public void onExhausted(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(new Skeleton()));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PrinceCatacombMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
