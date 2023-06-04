package shadowverse.cards.Nemesis.Condemned;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceToDrawPileAction;
import shadowverse.action.Choose2DifferentAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class DimensionCrack
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:DimensionCrack";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DimensionCrack");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DimensionCrack.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new AnalyzeArtifact());
        list.add(new AncientArtifact());
        list.add(new EdgeArtifact());
        list.add(new BlitzArtifact());
        return list;
    }

    public DimensionCrack() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF,3);
        ExhaustiveVariable.setBaseValue(this, 1);
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
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.upgrade(this, 1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard[] list = new AbstractCard[returnChoice().size()];
        returnChoice().toArray(list);
        if (upgraded){
            for (AbstractCard c : list){
                c.upgrade();
            }
        }
        addToBot(new Choose2DifferentAction(true,2,list));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard[] list = new AbstractCard[returnChoice().size()];
        returnChoice().toArray(list);
        if (upgraded){
            for (AbstractCard c : list){
                c.upgrade();
            }
        }
        addToBot(new ChoiceToDrawPileAction(true,list));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DimensionCrack();
    }
}


