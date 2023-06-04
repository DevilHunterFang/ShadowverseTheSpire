package shadowverse.cards.Necromancer.Default;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Necromancer;

public class RegenerateSpirit
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:RegenerateSpirit";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RegenerateSpirit");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RegenerateSpirit.png";

    public RegenerateSpirit() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE, 2);
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            ;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReanimateAction(1));
        addToBot(new ReanimateAction(2));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReanimateAction(1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RegenerateSpirit();
    }
}


