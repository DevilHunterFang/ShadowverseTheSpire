package shadowverse.cards.Elf.Default;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Elf;


public class MarkOfUnkilling
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:MarkOfUnkilling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MarkOfUnkilling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MarkOfUnkilling.png";

    public MarkOfUnkilling() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY, 2);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        if (m.powers.stream().anyMatch(pow -> pow.type == AbstractPower.PowerType.DEBUFF))
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        addToBot(new DrawCardAction(1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MarkOfUnkilling();
    }
}

