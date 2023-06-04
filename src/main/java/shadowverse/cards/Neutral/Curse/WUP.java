package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.BelphometStatus;
import shadowverse.characters.AbstractShadowversePlayer;

public class WUP extends CustomCard {
    public static final String ID = "shadowverse:WUP";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WUP");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WUP.png";

    public WUP() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = new BelphometStatus();
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
    }


    @Override
    public void triggerWhenDrawn() {
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(new BelphometStatus()));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(new BelphometStatus()));
        boolean has = false;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c != this && (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE) || c.hasTag(AbstractShadowversePlayer.Enums.NATURAL))) {
                has = true;
                break;
            }
        }
        if (!has) {
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(new BelphometStatus()));
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new WUP();
    }
}
