package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Royal;
import shadowverse.powers.SupportCannonPower;

import java.util.ArrayList;

public class LeciaSkySaber extends CustomCard {
    public static final String ID = "shadowverse:LeciaSkySaber";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LeciaSkySaber.png";
    public static final String IMG_PATH_EV = "img/cards/LeciaSkySaber_Ev.png";

    private int previewIndex;
    private float rotationTimer;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new NanoTheDawnblade());
        list.add(new LeciaNano());
        list.add(new TwilightBlade());
        return list;
    }

    public LeciaSkySaber() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, AbstractCard.CardType.ATTACK, Royal.Enums.COLOR_YELLOW, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 8;
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered && this.upgraded) {
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                if (this.previewIndex == returnProphecy().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            initializeDescription();
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new SupportCannonPower(abstractPlayer, this.magicNumber), this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        boolean have = false;
        AbstractCard c = null;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.cardID.equals("shadowverse:NanoTheDawnblade")) {
                have = true;
                c = card;
                break;
            }
        }
        if (have) {
//            if (AbstractDungeon.player.hand.group.contains(this)) {
                addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eff2"));
                addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
                AbstractCard card = new LeciaNano();
                this.addToTop(new MakeTempCardInHandAction(card, 1));
                if (this.upgraded) {
                    card.upgrade();
                }
//            }
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eff"));
            AbstractCard card = new NanoTheDawnblade();
            this.addToTop(new MakeTempCardInHandAction(card, 1));
            if (this.upgraded) {
                card.upgrade();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LeciaSkySaber();
    }
}