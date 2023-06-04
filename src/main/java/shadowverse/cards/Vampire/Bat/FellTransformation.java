package shadowverse.cards.Vampire.Bat;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ForestBat;
import shadowverse.characters.Vampire;

public class FellTransformation
        extends CustomCard {
    public static final String ID = "shadowverse:FellTransformation";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FellTransformation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;
    public static final String IMG_PATH = "img/cards/FellTransformation.png";

    public FellTransformation() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.NONE);
        this.cardsToPreview = new ForestBat();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return true;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new FellTransformation();
    }
}

