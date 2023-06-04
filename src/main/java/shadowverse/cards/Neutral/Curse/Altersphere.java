package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Altersphere extends CustomCard {
    public static final String ID = "shadowverse:Altersphere";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Altersphere");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Altersphere.png";

    public Altersphere() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        int rnd = AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.hand.size() - 1);
        AbstractCard toExhaust = null;
        toExhaust = AbstractDungeon.player.hand.group.get(rnd);
        addToBot((AbstractGameAction) new ExhaustSpecificCardAction(toExhaust, AbstractDungeon.player.hand));
        CardRarity crarity = c.rarity;
        if (crarity == CardRarity.SPECIAL) {
            crarity = CardRarity.COMMON;
        }
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(CardLibrary.getAnyColorCard(crarity)));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Altersphere();
    }
}