package shadowverse.cards.Neutral.Temp;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Elf;

public class BlessingsOfCreation extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:BlessingsOfCreation";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlessingsOfCreation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BlessingsOfCreation.png";

    public BlessingsOfCreation() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.NONE,1);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BlessingsOfCreation"));
        addToBot(new DrawCardAction(this.magicNumber));
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
        AbstractDungeon.actionManager.cardsPlayedThisTurn.add(this);
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("BlessingsOfCreation"));
        addToBot(new DrawCardAction(this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return new BlessingsOfCreation();
    }
}
