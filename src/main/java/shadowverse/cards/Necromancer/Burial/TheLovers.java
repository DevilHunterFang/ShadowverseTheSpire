package shadowverse.cards.Necromancer.Burial;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.action.BurialAction;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;
import shadowverse.powers.TheLoversPower;


public class TheLovers extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:TheLovers";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheLovers");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheLovers.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/TheLovers_L.png");

    public TheLovers() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF,6);
        this.baseBlock = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(6);
            upgradeMagicNumber(1);
        }
    }


    @Override
    public void triggerOnExhaust() {
        addToBot(new DrawCardAction(2));
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot(new SFXAction("TheLovers_L_Eh"));
        else
            addToBot(new SFXAction("TheLoversEH"));
        addToBot(new ApplyPowerAction(p, p, new TheLoversPower(p)));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot(new SFXAction("TheLovers_L"));
        else
            addToBot(new SFXAction("TheLovers"));
        int attackAmt = 0;
        for (AbstractCard c : p.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2 && this.costForTurn > 0) {
            addToBot(new BurialAction(2, null));
            int rand = AbstractDungeon.cardRandomRng.random(this.magicNumber);
            int rand2 = this.magicNumber - rand;
            addToBot(new ReanimateAction(rand));
            addToBot(new ReanimateAction(rand2));
            if (p.hasPower(MementoPower.POWER_ID)){
                int rand3 = AbstractDungeon.cardRandomRng.random(this.magicNumber);
                int rand4 = this.magicNumber - rand;
                addToBot(new ReanimateAction(rand3));
                addToBot(new ReanimateAction(rand4));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheLovers();
    }
}

