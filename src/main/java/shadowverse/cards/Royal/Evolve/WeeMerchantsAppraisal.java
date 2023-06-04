package shadowverse.cards.Royal.Evolve;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class WeeMerchantsAppraisal extends CustomCard {
    public static final String ID = "shadowverse:WeeMerchantsAppraisal";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WeeMerchantsAppraisal");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WeeMerchantsAppraisal.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedGoblet());
        list.add(new GildedNecklace());
        list.add(new EvolutionPoint());
        return list;
    }

    public WeeMerchantsAppraisal() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered) {
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
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
        addToBot(new MakeTempCardInHandAction(new GildedGoblet()));
        addToBot(new MakeTempCardInHandAction(new GildedNecklace()));
        if (this.upgraded) {
            addToBot(new DrawCardAction(p, this.magicNumber));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new WeeMerchantsAppraisal();
    }
}
