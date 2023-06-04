package shadowverse.cards.Witch.Academic;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction2;
import shadowverse.action.DrawPileToHandAction_Tag_NOREPEAT;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;

public class Hanna extends CustomCard {
    public static final String ID = "shadowverse:Hanna";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Hanna");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Hanna.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MysterianCircle());
        list.add(new MysterianMissile());
        list.add(new MysterianRite());
        return list;
    }

    public Hanna() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
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

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ((AbstractShadowversePlayer) abstractPlayer).mysteriaCount++;
        addToBot((AbstractGameAction) new SFXAction("Hanna"));
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        AbstractCard a = new MysterianCircle();
        AbstractCard b = new MysterianMissile();
        AbstractCard c = new MysterianRite();
        if (this.upgraded){
            a.upgrade();
            b.upgrade();
            c.upgrade();
        }
        addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { a, b, c }));
        addToBot((AbstractGameAction) new DrawPileToHandAction_Tag_NOREPEAT(1, AbstractShadowversePlayer.Enums.MYSTERIA, null, this));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Hanna();
    }
}

