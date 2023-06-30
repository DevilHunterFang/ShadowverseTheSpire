package shadowverse.augement;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.action.NecromanceAction;

public class WindMod extends AbstractAugment {

    //The ID should be prefixed with your modID.
    public static final String ID = "shadowverse:WindMod";
    //You can store the text in whatever format, this example uses UIStrings
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }

    @Override
    protected boolean validCard(AbstractCard card) {
        return card.cost != -2
                && card.type == AbstractCard.CardType.SKILL
                && card.baseDamage < 1
                && card.baseBlock > 1;
    }

    @Override
    public String getPrefix() {
        return TEXT[0];
    }

    @Override
    public String getSuffix() {
        return TEXT[1];
    }

    public float modifyBaseBlock(float block, AbstractCard card) {
        return card.baseBlock > 1 ? block * 0.6666667F : block;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractPlayer abstractPlayer = AbstractDungeon.player;
        addToBot((AbstractGameAction)new NecromanceAction(6,
                null,
                new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(10, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true)
        ));
    }

    public String getAugmentDescription() {
        return TEXT[2];
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return insertAfterText(rawDescription, CARD_TEXT[0]);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new WindMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
