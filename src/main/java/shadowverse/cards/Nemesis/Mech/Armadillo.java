package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.cards.Neutral.Temp.RepairMode;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Armadillo
        extends CustomCard {
    public static final String ID = "shadowverse:Armadillo";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Armadillo");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Armadillo.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProductMachine());
        list.add(new RepairMode());
        return list;
    }

    public Armadillo() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
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
            upgradeBlock(3);
        }
    }

    @Override
    public void triggerOnExhaust(){
        AbstractCard product = (AbstractCard)new ProductMachine();
        AbstractCard repair = (AbstractCard)new RepairMode();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(product));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(repair));
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractCard product = (AbstractCard)new ProductMachine();
        AbstractCard repair = (AbstractCard)new RepairMode();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(product));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(repair));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Armadillo();
    }
}


