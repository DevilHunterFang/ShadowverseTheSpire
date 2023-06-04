package shadowverse.cards.Neutral.Temp;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Vampire;
import shadowverse.powers.WingsOfDesirePower;

public class WingsOfDesire extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:WingsOfDesire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WingsOfDesire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WingsOfDesire.png";

    public WingsOfDesire() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF, 1);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
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
        addToBot(new SFXAction("WingsOfDesire_EH"));
        addToBot(new ApplyPowerAction(p, p, new WingsOfDesirePower(p, 1, this.magicNumber), 1));
        addToBot(new ApplyPowerAction(p, p, new WingsOfDesirePower(p, 1, this.magicNumber), 1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("WingsOfDesire"));
        addToBot(new ApplyPowerAction(p, p, new WingsOfDesirePower(p, 1, this.magicNumber), 1));
    }
}
