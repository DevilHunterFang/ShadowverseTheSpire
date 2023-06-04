package shadowverse.cards.Witch.Default;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


public class DevotedResearcher extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:DevotedResearcher";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DevotedResearcher");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DevotedResearcher.png";

    public DevotedResearcher() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF, 2);
        this.baseBlock = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.cardsToPreview = new VolunteerTestSubject();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("DevotedResearcher_EH"));
        addToBot(new GainBlockAction(p, this.block));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(c, 1));
        addToBot(new MakeTempCardInHandAction(c, 1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("DevotedResearcher"));
        addToBot(new GainBlockAction(p, this.block));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(c, 1));
    }

    public AbstractCard makeCopy() {
        return new DevotedResearcher();
    }
}
