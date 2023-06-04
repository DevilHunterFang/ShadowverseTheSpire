package shadowverse.cards.Vampire.Avarice;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;

public class MoonriseWerewolf
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:MoonriseWerewolf";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MoonriseWerewolf");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MoonriseWerewolf.png";

    public MoonriseWerewolf() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF, 2);
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("MoonriseWerewolf_EH"));
        addToBot(new GainBlockAction(p, p, this.block));
        if (p.hasPower(AvaricePower.POWER_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 2), 2));
        }
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("MoonriseWerewolf"));
        addToBot(new GainBlockAction(p, p, this.block));
        if (p.hasPower(AvaricePower.POWER_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 2), 2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MoonriseWerewolf();
    }
}


