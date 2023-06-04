package shadowverse.cards.Necromancer.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.LunaDoll;
import shadowverse.cards.Neutral.Temp.NecroTemptation;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;

public class Luna
        extends CustomCard {
    public static final String ID = "shadowverse:Luna";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Luna");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Luna.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new LunaDoll());
        list.add(new NecroTemptation());
        return list;
    }

    public Luna() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
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
        AbstractCard doll = (AbstractCard) new LunaDoll();
        AbstractCard temptation = (AbstractCard) new NecroTemptation();
        if (this.upgraded) {
            doll.upgrade();
            temptation.upgrade();
        }
        addToBot((AbstractGameAction) new SFXAction("Luna"));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(doll));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(temptation));
        addToBot((AbstractGameAction)new NecromanceAction(6,null,new AbstractGameAction[]{
                (AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint()),
                (AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle())
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Luna();
    }
}

