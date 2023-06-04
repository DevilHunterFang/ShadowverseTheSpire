package shadowverse.cards.Royal.Minion;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.powers.MarsPower;

public class Mars extends CustomCard {
    public static final String ID = "shadowverse:Mars";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Mars");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Mars.png";

    public Mars() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
            this.isEthereal = false;
//            this.upgradeBaseCost(2);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new ApplyPowerAction(p, p, new MarsPower(p, this.magicNumber),this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new Mars();
    }
}
