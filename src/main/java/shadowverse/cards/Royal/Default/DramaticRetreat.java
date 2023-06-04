package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DramaticRetreatAction;
import shadowverse.characters.Royal;

public class DramaticRetreat extends CustomCard {
    public static final String ID = "shadowverse:DramaticRetreat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DramaticRetreat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DramaticRetreat.png";

    public DramaticRetreat() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        boolean hasAttack = false;
        for (AbstractCard c : p.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                hasAttack = true;
                break;
            }
        }
        if (!hasAttack) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            canUse = false;
        }
        return canUse;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> card.type == CardType.ATTACK, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                addToBot(new DramaticRetreatAction(c,p.hand));
            }
        }));
        addToBot(new DrawCardAction(p, this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new DramaticRetreat();
    }
}
