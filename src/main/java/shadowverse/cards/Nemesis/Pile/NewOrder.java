package shadowverse.cards.Nemesis.Pile;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class NewOrder
        extends CustomCard {
    public static final String ID = "shadowverse:NewOrder";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewOrder");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NewOrder.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MysticArtifact());
        list.add(new RadiantArtifact());
        list.add(new PrimeArtifact());
        return list;
    }

    public NewOrder() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
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
            upgradeBaseCost(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("NewOrder"));
        for (AbstractCard c : p.drawPile.group) {
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, p.drawPile));
        }
        ArrayList<AbstractCard> artifactDeck = returnChoice();
        for (int i=0;i<10;i++){
            for (AbstractCard ca : artifactDeck) {
                addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(ca, 1, true, true));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new NewOrder();
    }
}


