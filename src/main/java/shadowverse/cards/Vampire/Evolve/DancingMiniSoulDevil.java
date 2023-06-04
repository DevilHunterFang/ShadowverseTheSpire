package shadowverse.cards.Vampire.Evolve;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Vampire;
import shadowverse.relics.KagemitsuSword;


public class DancingMiniSoulDevil
        extends CustomCard {
    public static final String ID = "shadowverse:DancingMiniSoulDevil";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DancingMiniSoulDevil");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DancingMiniSoulDevil.png";

    public DancingMiniSoulDevil() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.cardsToPreview = new EvolutionPoint();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("DancingMiniSoulDevil"));
        addToBot(new GainBlockAction(p, this.block));
        if (this.upgraded) {
            this.degrade();
            if (p.hasRelic(KagemitsuSword.ID)) {
                this.upgrade();
            }
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof EvolutionPoint && !this.upgraded) {
            this.upgrade();
            addToBot(new SFXAction("DancingMiniSoulDevil_Eff"));
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new DancingMiniSoulDevil();
    }
}

