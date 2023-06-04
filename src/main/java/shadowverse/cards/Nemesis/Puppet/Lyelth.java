package shadowverse.cards.Nemesis.Puppet;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Lyelth
        extends CustomCard {
    public static final String ID = "shadowverse:Lyelth";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lyelth");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new LyelthMarionette());
        list.add(new UnseenStrength());
        list.add(new EnhancedPuppet());
        return list;
    }

    public static final String IMG_PATH = "img/cards/Lyelth.png";

    public Lyelth() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
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
                if (this.upgraded){
                    if (this.cardsToPreview instanceof UnseenStrength)
                        this.cardsToPreview.upgrade();
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Lyelth"));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot( new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return true;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                addToBot( new ExhaustSpecificCardAction(c, abstractPlayer.hand));
                addToBot( new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, true, false));
                addToBot(new MakeTempCardInHandAction(new LyelthMarionette()));
                AbstractCard u = new UnseenStrength();
                if (upgraded)
                    u.upgrade();
                addToBot(new MakeTempCardInHandAction(u));
            }
        }));
        if (upgraded)
            addToBot(new MakeTempCardInHandAction(new EnhancedPuppet()));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lyelth();
    }
}

