package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.LegendSwordCommanderPower;

public class LegendSwordCommander extends CustomCard {
    public static final String ID = "shadowverse:LegendSwordCommander";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LegendSwordCommander");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LegendSwordCommander.png";

    public LegendSwordCommander() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 7;
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new ApplyPowerAction(p, p, new BarricadePower(p)));
        addToBot(new ApplyPowerAction(p, p, new LegendSwordCommanderPower(p,this.magicNumber),this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new LegendSwordCommander();
    }
}
