package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;

public class CondemnedMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:CondemnedMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.UNCOMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return !card.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED)
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
        if (!card.hasTag(AbstractShadowversePlayer.Enums.CONDEMNED)) {
            card.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        }
    }

    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return card.baseDamage > 1 ? damage * 1.2F : damage;
    }

    public float modifyBaseBlock(float block, AbstractCard card) {
        return card.baseBlock > 1 ? block * 1.2F : block;
    }

    public String getAugmentDescription() {
        return TEXT[2];
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CARD_TEXT[0] + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CondemnedMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
