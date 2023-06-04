package shadowverse.cards.Vampire.Academic;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

public class BadGirlLife
        extends CustomCard {
    public static final String ID = "shadowverse:BadGirlLife";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BadGirlLife");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;
    public static final String IMG_PATH = "img/cards/BadGirlLife.png";

    public BadGirlLife() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new EvolutionPoint();
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BadGirlLife"));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return upgraded?card.type == CardType.ATTACK:(card.type == CardType.ATTACK&&card.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC));
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        p.hand.moveToDiscardPile(c);
                        c.triggerOnManualDiscard();
                        GameActionManager.incrementDiscard(false);
                        addToBot(new DrawCardAction(2));
                        this.isDone = true;
                    }
                });
                if (p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
                    addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                }
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BadGirlLife();
    }
}

