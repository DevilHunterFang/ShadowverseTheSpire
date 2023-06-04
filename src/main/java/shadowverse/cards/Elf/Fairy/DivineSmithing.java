package shadowverse.cards.Elf.Fairy;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DivineSmithingAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Elf;

public class DivineSmithing
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:DivineSmithing";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DivineSmithing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DivineSmithing.png";

    public DivineSmithing() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF,1);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DivineSmithingAction(true));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DivineSmithingAction(false));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DivineSmithing();
    }
}


