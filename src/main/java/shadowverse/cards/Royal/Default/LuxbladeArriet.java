package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Royal;

public class LuxbladeArriet extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LuxbladeArriet");
    public static final String ID = "shadowverse:LuxbladeArriet";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LuxbladeArriet.png";

    public LuxbladeArriet() {
        this(0);
    }

    public LuxbladeArriet(int upgrades) {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(CardTags.HEALING);
        this.baseBlock = 10;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        this.selfRetain = true;
        if (this.timesUpgraded >= 1) {
            this.isInnate = true;
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return this.timesUpgraded < 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new GainBlockAction(p, p, this.magicNumber));
        if (this.timesUpgraded >= 1) {
            addToBot(new GainBlockAction(p, p, this.block));
        }
        if (this.timesUpgraded >= 2) {
            this.addToTop(new GainEnergyAction(2));
        }
        if (this.timesUpgraded >= 3) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        }
        if (this.timesUpgraded >= 4) {
            this.addToBot(new ExpertiseAction(p, 7));
        }
        if (this.timesUpgraded >= 5) {
            addToBot(new HealAction(p, p, this.magicNumber));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = "";
        if (this.timesUpgraded >= 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        }
        if (this.timesUpgraded >= 2) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        }
        if (this.timesUpgraded >= 3) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        if (this.timesUpgraded >= 4) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        }
        if (this.timesUpgraded >= 5) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = "";
        if (this.timesUpgraded >= 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        }
        if (this.timesUpgraded >= 2) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        }
        if (this.timesUpgraded >= 3) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        if (this.timesUpgraded >= 4) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        }
        if (this.timesUpgraded >= 5) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuxbladeArriet();
    }
}
