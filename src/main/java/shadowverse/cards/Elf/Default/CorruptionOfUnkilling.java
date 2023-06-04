package shadowverse.cards.Elf.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Elf;
import shadowverse.powers.IzudiaCorruptionPower;


public class CorruptionOfUnkilling extends CustomCard {
    public static final String ID = "shadowverse:CorruptionOfUnkilling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CorruptionOfUnkilling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CorruptionOfUnkilling.png";

    public CorruptionOfUnkilling() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("CorruptionOfUnkilling"));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower) new IzudiaCorruptionPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new CorruptionOfUnkilling();
    }
}
