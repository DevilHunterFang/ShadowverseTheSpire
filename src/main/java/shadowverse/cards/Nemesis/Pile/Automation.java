package shadowverse.cards.Nemesis.Pile;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceToDrawPileAction;
import shadowverse.cards.Neutral.Temp.AnalyzeArtifact;
import shadowverse.cards.Neutral.Temp.AncientArtifact;
import shadowverse.cards.Neutral.Temp.MysticArtifact;
import shadowverse.cards.Neutral.Temp.RadiantArtifact;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Automation
        extends CustomCard {
    public static final String ID = "shadowverse:Automation";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Automation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Automation.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new AnalyzeArtifact());
        list.add(new AncientArtifact());
        list.add(new MysticArtifact());
        list.add(new RadiantArtifact());
        return list;
    }

    public Automation() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
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
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractCard[] list = new AbstractCard[returnChoice().size()];
        returnChoice().toArray(list);
        addToBot((AbstractGameAction)new ChoiceToDrawPileAction(true,list));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Automation();
    }
}


