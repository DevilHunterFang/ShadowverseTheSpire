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
import shadowverse.powers.EternalDogmaPower;

public class EternalDogma extends CustomCard {
    public static final String ID = "shadowverse:EternalDogma";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EternalDogma");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EternalDogma.png";

    public EternalDogma() {
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
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EternalDogmaPower(AbstractDungeon.player, 2)));
    }

    public AbstractCard makeCopy() {
        return new EternalDogma();
    }
}
