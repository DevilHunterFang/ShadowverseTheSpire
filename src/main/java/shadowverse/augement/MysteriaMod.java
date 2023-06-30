package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Neutral.Temp.MysterianCircle;
import shadowverse.cards.Neutral.Temp.MysterianMissile;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

public class MysteriaMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:MysteriaMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return !card.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)
                && !card.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC)
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
    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)) {
            card.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
        }
        if (!card.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC)) {
            card.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        ArrayList<AbstractCard> list = new ArrayList<>();
        MysterianCircle mysterianCircle = new MysterianCircle();
        MysterianMissile mysterianMissile = new MysterianMissile();
        list.add(mysterianCircle);
        list.add(mysterianMissile);
        AbstractCard c = list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1)).makeCopy();
        addToBot(new MakeTempCardInHandAction(c, 1));
    }

    public String getAugmentDescription() {
        return TEXT[2];
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CARD_TEXT[0] + insertAfterText(rawDescription, CARD_TEXT[1]);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MysteriaMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
