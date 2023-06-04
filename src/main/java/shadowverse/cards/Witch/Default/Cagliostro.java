package shadowverse.cards.Witch.Default;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.ArsMagna;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

public class Cagliostro
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:Cagliostro";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cagliostro");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cagliostro.png";

    public Cagliostro() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF,2);
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.cardsToPreview = new ArsMagna();
        this.baseBlock = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (p.hasPower(EarthEssence.POWER_ID)) {
            addToBot(new SFXAction("Cagliostro2"));
            ((AbstractShadowversePlayer) p).earthCount++;
            if (EnergyPanel.getCurrentEnergy() - this.costForTurn >= 2)
                c.setCostForTurn(2);
            addToBot(new MakeTempCardInHandAction(c, 1));
            addToBot(new ApplyPowerAction(p, p, new EarthEssence(p, -1), -1));
        } else {
            addToBot(new SFXAction("Cagliostro1"));
        }
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new EarthEssence(p, 1), 1));
        addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
        addToBot(new MakeTempCardInHandAction(new Miracle()));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (p.hasPower(EarthEssence.POWER_ID)) {
            addToBot(new SFXAction("Cagliostro2"));
            ((AbstractShadowversePlayer) p).earthCount++;
            if (EnergyPanel.getCurrentEnergy() - this.costForTurn >= 2)
                c.setCostForTurn(2);
            addToBot(new MakeTempCardInHandAction(c, 1));
            addToBot(new ApplyPowerAction(p, p, new EarthEssence(p, -1), -1));
        } else {
            addToBot(new SFXAction("Cagliostro1"));
        }
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new EarthEssence(p, 1), 1));
    }


    public AbstractCard makeCopy() {
        return new Cagliostro();
    }
}
