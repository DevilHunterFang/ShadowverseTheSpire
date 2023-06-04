package shadowverse.cards.Nemesis.Condemned;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.BlitzArtifact;
import shadowverse.cards.Neutral.Temp.ProtectArtifact;
import shadowverse.characters.Nemesis;
import java.util.ArrayList;

public class ANewDiscovery
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:ANewDiscovery";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ANewDiscovery");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ANewDiscovery.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProtectArtifact());
        list.add(new BlitzArtifact());
        return list;
    }

    public ANewDiscovery() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.NONE, 3);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
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

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard pr = new ProtectArtifact();
        AbstractCard e = new BlitzArtifact();
        if (this.upgraded) {
            pr.upgrade();
            e.upgrade();
        }
        pr.setCostForTurn(0);
        e.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(pr));
        addToBot(new MakeTempCardInHandAction(e));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        AbstractCard pr = new ProtectArtifact();
        AbstractCard e = new BlitzArtifact();
        if (this.upgraded) {
            pr.upgrade();
            e.upgrade();
        }
        addToBot((AbstractGameAction) new ChoiceAction(new AbstractCard[]{pr, e,}));
    }


    public AbstractCard makeCopy() {
        return new ANewDiscovery();
    }
}


