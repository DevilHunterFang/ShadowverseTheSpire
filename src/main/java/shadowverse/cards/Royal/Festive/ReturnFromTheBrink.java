package shadowverse.cards.Royal.Festive;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class ReturnFromTheBrink extends CustomCard {
    public static final String ID = "shadowverse:ReturnFromTheBrink";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ReturnFromTheBrink");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ReturnFromTheBrink.png";
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ArmamentsAction").TEXT;
    private static final String TEXT1 = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];
    private static final String TEXT2 = CardCrawlGame.languagePack.getUIString("shadowverse:Ethereal").TEXT[0];

    public ReturnFromTheBrink() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (card.type == CardType.ATTACK) {
                cards.add(card);
            }
        }
        if (cards.size() <= this.magicNumber) {
            for (AbstractCard c : cards) {
                getCard(c);
            }
        } else {
            int s = cards.size();
            if (this.magicNumber == 1) {
                int roll = AbstractDungeon.cardRandomRng.random(s - 1);
                AbstractCard c = cards.get(roll);
                getCard(c);
            } else {
                int roll1 = AbstractDungeon.cardRandomRng.random(s - 1);
                int roll2;
                for (roll2 = AbstractDungeon.cardRandomRng.random(s - 1); roll2 == roll1; roll2 = AbstractDungeon.cardRandomRng.random(s - 1)) {
                }
                AbstractCard c1 = cards.get(roll1);
                AbstractCard c2 = cards.get(roll2);
                getCard(c1);
                getCard(c2);
            }

        }
    }

    public void getCard(AbstractCard c) {
        AbstractCard tmp = c.makeStatEquivalentCopy();
        tmp.exhaustOnUseOnce = true;
        tmp.exhaust = true;
        tmp.isEthereal = true;
        tmp.rawDescription += " NL " + TEXT2 + " 。 NL " + TEXT1 + " 。";
        tmp.costForTurn = 0;
        tmp.isCostModified = true;
        tmp.initializeDescription();
        tmp.applyPowers();
        AbstractDungeon.player.hand.addToTop(tmp);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReturnFromTheBrink();
    }
}
