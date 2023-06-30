package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;

public class NaturaMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:NaturaMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return !card.hasTag(AbstractShadowversePlayer.Enums.NATURAL)
                && (card.type == AbstractCard.CardType.ATTACK)
                && (card.baseDamage > 1 || card.baseBlock > 1);
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
        if (!card.hasTag(AbstractShadowversePlayer.Enums.NATURAL)) {
            card.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        }
    }

    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return card.baseDamage > 1 ? damage * 0.6666667F : damage;
    }

    public float modifyBaseBlock(float block, AbstractCard card) {
        return card.baseBlock > 1 ? block * 0.6666667F : block;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new MakeTempCardInHandAction(new NaterranGreatTree(), 1));
    }

    public String getAugmentDescription() {
        return TEXT[2];
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CARD_TEXT[0] + insertAfterText(rawDescription, CARD_TEXT[1]);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new NaturaMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
