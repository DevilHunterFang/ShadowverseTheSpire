package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Orchid1 extends CustomCard {
    public static final String ID = "shadowverse:Orchid1";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Orchid1");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Orchid1.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Uno());
        list.add(new Due());
        return list;
    }

    public Orchid1() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
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


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction)new SFXAction("Orchid1"));
        int dAmt = 0;
        for (AbstractCard c: AbstractDungeon.player.hand.group){
            if (c instanceof Puppet){
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                addToBot((AbstractGameAction)new SFXAction("Orchid1Effect"));
                dAmt++;
            }
        }
        dAmt--;
        AbstractCard uno = (AbstractCard)new Uno();
        AbstractCard due = (AbstractCard)new Due();
        if (this.upgraded){
            uno.upgrade();
            due.upgrade();
        }
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(uno));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(due,dAmt));
    }

    public AbstractCard makeCopy() {
        return new Orchid1();
    }
}
