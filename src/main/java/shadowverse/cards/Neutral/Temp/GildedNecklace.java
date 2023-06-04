package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionBuffAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class GildedNecklace extends CustomCard {
    public static final String ID = "shadowverse:GildedNecklace";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GildedNecklace");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GildedNecklace.png";

    public GildedNecklace() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.GILDED);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new MinionBuffAction(1, this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new GildedNecklace();
    }
}

