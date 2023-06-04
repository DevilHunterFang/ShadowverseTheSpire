package shadowverse.cards.Royal.Minion;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Royal;
import shadowverse.orbs.ShieldGuardian;

public class EmpressOfSerenity extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:EmpressOfSerenity";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EmpressOfSerenity.png";

    public EmpressOfSerenity() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF, 2);
        this.baseBlock = 6;
    }

    @Override
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
        addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
        addToBot(new MinionSummonAction(new ShieldGuardian()));
        addToBot(new MinionSummonAction(new ShieldGuardian()));
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new MinionSummonAction(new ShieldGuardian()));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new MinionSummonAction(new ShieldGuardian()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EmpressOfSerenity();
    }
}
