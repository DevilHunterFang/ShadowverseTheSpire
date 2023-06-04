package shadowverse.cards.Witch.Earth1;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.ConjureGuardian;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;


public class GolemAssault
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:GolemAssault";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GolemAssault");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GolemAssault.png";


    public GolemAssault() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF,
                2);
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.cardsToPreview = new ConjureGuardian();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (p.hasPower(EarthEssence.POWER_ID)) {
            if (p instanceof AbstractShadowversePlayer) {
                ((AbstractShadowversePlayer) p).earthCount++;
            }
            c.setCostForTurn(0);
            addToBot(new ApplyPowerAction(p, p,  new EarthEssence(p, -1), -1));
        }
        if (this.upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(c, 3));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        if (p.hasPower(EarthEssence.POWER_ID)) {
            if (p instanceof AbstractShadowversePlayer) {
                ((AbstractShadowversePlayer) p).earthCount++;
            }
            c.setCostForTurn(0);
            addToBot(new ApplyPowerAction(p, p,  new EarthEssence(p, -1), -1));
        }
        if (this.upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(c, 1));
    }


    public AbstractCard makeCopy() {
        return new GolemAssault();
    }
}

