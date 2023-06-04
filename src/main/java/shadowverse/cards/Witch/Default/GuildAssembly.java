package shadowverse.cards.Witch.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Witchcraft;

public class GuildAssembly extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GuildAssembly");
    public static final String ID = "shadowverse:GuildAssembly";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GuildAssembly.png";

    public GuildAssembly() {
        this(0);
    }

    public GuildAssembly(int upgrades) {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.timesUpgraded = upgrades;
        this.isInnate = true;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        this.selfRetain = true;
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return this.timesUpgraded < 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("GuildAssembly"));
        addToBot(new DrawCardAction(1));
        if (this.timesUpgraded >= 1) {
            addToBot(new DrawCardAction(1));
        }
        if (this.timesUpgraded >= 2) {
            addToBot(new GainBlockAction(p, p, this.block));
        }
        if (this.timesUpgraded >= 3) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
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
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
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
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuildAssembly();
    }
}
