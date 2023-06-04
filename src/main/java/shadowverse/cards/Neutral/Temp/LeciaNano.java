package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import shadowverse.cards.Royal.Default.LeciaSkySaber;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class LeciaNano extends CustomCard {
    public static final String ID = "shadowverse:LeciaNano";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LeciaNano.png";

    private int previewIndex;
    private float rotationTimer;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new LeciaSkySaber());
        list.add(new NanoTheDawnblade());
        list.add(new TwilightBlade());
        return list;
    }

    public LeciaNano() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber=this.magicNumber=1;
        this.exhaust=true;
        this.isEthereal=true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new TwilightBlade()));
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard card1 = new LeciaSkySaber();
        this.addToTop(new MakeTempCardInHandAction(card1, 1));
        if (this.upgraded) {
            card1.upgrade();
        }
        AbstractCard card2 = new NanoTheDawnblade();
        this.addToTop(new MakeTempCardInHandAction(card2, 1));
        if (this.upgraded) {
            card2.upgrade();
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new LeciaNano();
    }
}

