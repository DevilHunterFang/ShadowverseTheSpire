package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;

public class PeckishOwlcat
        extends CustomCard {
    public static final String ID = "shadowverse:PeckishOwlcat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PeckishOwlcat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PeckishOwlcat.png";

    public PeckishOwlcat(int upgrades) {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 6;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
    }


    public void upgrade() {
        upgradeBlock(2 + this.timesUpgraded);
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("PeckishOwlcat"));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new PeckishOwlcat(this.timesUpgraded);
    }
}

