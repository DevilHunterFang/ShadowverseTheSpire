package shadowverse.cards.Witch.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import shadowverse.cards.Neutral.Temp.MysterianCircle;
import shadowverse.cards.Neutral.Temp.MysterianMissile;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;

public class MysterianKnowledge
        extends CustomCard {
    public static final String ID = "shadowverse:MysterianKnowledge";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysterianKnowledge");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private float rotationTimer;
    private int previewIndex;

    public static AbstractCard returnTrulyRandomMysterianInCombat(Random rng) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        MysterianCircle mysterianCircle = new MysterianCircle();
        MysterianMissile mysterianMissile = new MysterianMissile();
        list.add(mysterianCircle);
        list.add(mysterianMissile);
        return list.get(rng.random(list.size() - 1));
    }

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MysterianCircle());
        list.add(new MysterianMissile());
        return list;
    }

    public static final String IMG_PATH = "img/cards/MysterianKnowledge.png";

    public MysterianKnowledge() {
        super("shadowverse:MysterianKnowledge", NAME, "img/cards/MysterianKnowledge.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
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
                this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
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

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        AbstractCard c = returnTrulyRandomMysterianInCombat(AbstractDungeon.cardRandomRng).makeCopy();
        if (this.upgraded) {
            c.upgrade();
        }
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MysterianKnowledge();
    }
}

