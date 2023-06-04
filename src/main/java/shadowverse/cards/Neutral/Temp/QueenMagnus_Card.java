package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.orbs.QueenMagnus;

public class QueenMagnus_Card  extends CustomCard {
    public static final String ID = "shadowverse:QueenMagnus_Card";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/QueenMagnus.png";

    public QueenMagnus_Card() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new QueenMagnus()));
    }

    @Override
    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new QueenMagnus()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new QueenMagnus_Card();
    }
}

