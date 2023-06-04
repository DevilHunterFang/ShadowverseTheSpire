package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;
import shadowverse.powers.GuiltyShieldPower;

public class GuiltyShield extends CustomCard {
    public static final String ID = "shadowverse:GuiltyShield";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GuiltyShield");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GuiltyShield.png";

    public GuiltyShield() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        if (!AbstractDungeon.player.hasPower(GuiltyShieldPower.POWER_ID))
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GuiltyShieldPower(AbstractDungeon.player, 2)));
    }

    public AbstractCard makeCopy() {
        return new GuiltyShield();
    }
}
