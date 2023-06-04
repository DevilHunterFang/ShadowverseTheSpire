package shadowverse.cards.Elf.Default;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.TreacherousReversal;
import shadowverse.characters.Elf;

public class TheHanged
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:TheHanged";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheHanged");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheHanged.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/TheHanged_L.png");


    public TheHanged() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF, 3);
        this.cardsToPreview = new TreacherousReversal();
        this.baseBlock = 8;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("TheHanged_L_EH"));
        } else {
            addToBot(new SFXAction("TheHanged2"));
        }
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false))) {
            addToBot(new SFXAction("TheHanged_L"));
        } else {
            addToBot(new SFXAction("TheHanged1"));
        }
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        int count = 0;
        for (AbstractCard ca : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            count++;
        }
        if (count >= 3) {
            addToBot(new GainBlockAction(p, this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheHanged();
    }
}

