package shadowverse.cards.Neutral.Temp;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class SummonDivineTreasure
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:SummonDivineTreasure";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SummonDivineTreasure");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SummonDivineTreasure.png";
    private float rotationTimer;
    private int previewIndex;

    public SummonDivineTreasure() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF, 2, 3);
        ExhaustiveVariable.setBaseValue(this, 3);
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new JudgmentSpear());
        list.add(new GuiltyShield());
        list.add(new EternalDogma());
        list.add(new DivineTreasureHerald());
        list.add(new DivineTreasureGuardian());
        return list;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.upgrade(this, 2);
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
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new JudgmentSpear());
        stanceChoices.add(new GuiltyShield());
        stanceChoices.add(new EternalDogma());
        addToBot(new ChooseOneAction(stanceChoices));
        addToBot(new MakeTempCardInHandAction(new DivineTreasureGuardian()));
        addToBot(new MakeTempCardInHandAction(new DivineTreasureHerald()));
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new JudgmentSpear());
        stanceChoices.add(new GuiltyShield());
        stanceChoices.add(new EternalDogma());
        addToBot(new ChooseOneAction(stanceChoices));
        addToBot(new MakeTempCardInHandAction(new DivineTreasureHerald()));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new JudgmentSpear());
        stanceChoices.add(new GuiltyShield());
        stanceChoices.add(new EternalDogma());
        addToBot(new ChooseOneAction(stanceChoices));
    }


    public AbstractCard makeCopy() {
        return new SummonDivineTreasure();
    }
}

