package shadowverse.cards.Bishop.MechLion;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Bishop;

import java.util.ArrayList;

public class LionCrystal extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:LionCrystal";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LionCrystal");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LionCrystal.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new HolyShieldLion());
        list.add(new HolyPlateLion());
        list.add(new HolyKingLion());
        return list;
    }


    public LionCrystal() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE, 2);
    }


    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void triggerOnGlowCheck() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c instanceof LionCrystal || c instanceof LionCrystalCopy)
                count++;
        }
        if (count > 5 || Shadowverse.Enhance(enhanceCost)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.cardID.contains("shadowverse:LionCrystal"))
                count++;
        }
        if (count > 5) {
            AbstractCard k = new HolyKingLion();
            if (this.upgraded)
                k.upgrade();
            addToBot(new MakeTempCardInHandAction(k));
        } else if (count > 2) {
            AbstractCard pl = new HolyPlateLion();
            if (this.upgraded)
                pl.upgrade();
            addToBot(new MakeTempCardInHandAction(pl));
        } else {
            AbstractCard s = new HolyShieldLion();
            if (this.upgraded)
                s.upgrade();
            addToBot(new MakeTempCardInHandAction(s));
        }
        AbstractCard crs = new LionCrystalCopy();
        if (this.upgraded)
            crs.upgrade();
        addToBot(new MakeTempCardInHandAction(crs));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.cardID.contains("shadowverse:LionCrystal"))
                count++;
        }
        if (count > 5) {
            AbstractCard k = new HolyKingLion();
            if (this.upgraded)
                k.upgrade();
            addToBot(new MakeTempCardInHandAction(k));
        } else if (count > 2) {
            AbstractCard pl = new HolyPlateLion();
            if (this.upgraded)
                pl.upgrade();
            addToBot(new MakeTempCardInHandAction(pl));
        } else {
            AbstractCard s = new HolyShieldLion();
            if (this.upgraded)
                s.upgrade();
            addToBot(new MakeTempCardInHandAction(s));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new LionCrystal();
    }
}
