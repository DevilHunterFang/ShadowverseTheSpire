package shadowverse.cards.Royal.Ambush;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ElectroPower;
import shadowverse.action.GracefulManeuverAction;
import shadowverse.characters.Royal;

public class GracefulManeuver extends CustomCard {
    public static final String ID = "shadowverse:GracefulManeuver";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GracefulManeuver.png";


    public GracefulManeuver() {
        super(ID, NAME, IMG_PATH, -1, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!abstractPlayer.hasPower("Electrodynamics")) {
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ElectroPower(abstractPlayer)));
        }
        this.addToBot(new GracefulManeuverAction(abstractPlayer, this.energyOnUse, this.upgraded, this.freeToPlayOnce));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GracefulManeuver();
    }
}
