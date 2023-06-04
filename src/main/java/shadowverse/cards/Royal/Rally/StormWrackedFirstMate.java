package shadowverse.cards.Royal.Rally;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.DreadPirateFlag;
import shadowverse.cards.Neutral.Temp.GildedNecklace;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.StormWrackedFirstMatePower;

import java.util.ArrayList;

public class StormWrackedFirstMate extends CustomCard {
    public static final String ID = "shadowverse:StormWrackedFirstMate";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StormWrackedFirstMate");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/StormWrackedFirstMate.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedNecklace());
        list.add(new DreadPirateFlag());
        return list;
    }

    public StormWrackedFirstMate() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
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
            upgradeBlock(3);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new GainBlockAction(p,this.block));
        int hasGilded = 0;
        for (AbstractCard card:p.exhaustPile.group){
            if (card.hasTag(AbstractShadowversePlayer.Enums.GILDED))
                hasGilded++;
        }
        if (hasGilded>=7){
            addToBot(new MakeTempCardInHandAction(new DreadPirateFlag(),1));
        }else {
            addToBot(new MakeTempCardInHandAction(new GildedNecklace(),1));
        }
        addToBot(new ApplyPowerAction(p, p, new StormWrackedFirstMatePower(p, 1)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new StormWrackedFirstMate();
    }
}