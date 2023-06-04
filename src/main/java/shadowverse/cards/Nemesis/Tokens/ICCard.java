package shadowverse.cards.Nemesis.Tokens;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.action.InvocationAction;
import shadowverse.cards.Neutral.Temp.DeathPenalty;
import shadowverse.cards.Neutral.Temp.MagiTrainCard;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class ICCard
        extends CustomCard {
    public static final String ID = "shadowverse:ICCard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ICCard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ICCard.png";
    private static final Texture LEADER_SKIN_VERSION = ImageMaster.loadImage("img/cards/ICCard_L.png");
    public static boolean dupCheck = true;
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MagiTrainCard());
        list.add(new DeathPenalty());
        return list;
    }

    public ICCard() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 21;
        this.jokePortrait = new TextureAtlas.AtlasRegion(LEADER_SKIN_VERSION, 0, 0, LEADER_SKIN_VERSION.getWidth(), LEADER_SKIN_VERSION.getHeight());
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(7);
        }
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }


    public void atTurnStart() {
        int count = 0;
        for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
            if (card.type == CardType.ATTACK)
                count++;
        }
        if (count >= 20 && dupCheck) {
            dupCheck = false;
            if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new InvocationAction((AbstractCard) this));
            }
        } else if (count < 20) {
            dupCheck = true;
        }
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        dupCheck = true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        dupCheck = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if ((UnlockTracker.betaCardPref.getBoolean(this.cardID, false)))
            addToBot((AbstractGameAction) new SFXAction("IC_L"));
        else
            addToBot((AbstractGameAction) new SFXAction("IC1"));
        addToBot((AbstractGameAction) new WaitAction(0.8F));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
        AbstractCard c = (AbstractCard) new MagiTrainCard();
        c.freeToPlayOnce = true;
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c));
        if (this.costForTurn > 0)
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new DeathPenalty()));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ICCard();
    }
}


