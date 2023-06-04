package shadowverse.cards.Royal.Default;


import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MusketeersAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.cards.Neutral.Temp.Aramis;
import shadowverse.cards.Neutral.Temp.Athos;
import shadowverse.cards.Neutral.Temp.DArtagnan;
import shadowverse.cards.Neutral.Temp.Porthos;
import shadowverse.characters.Royal;

import java.util.ArrayList;


public class MusketeersVow extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:MusketeersVow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MusketeersVow.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoose() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Aramis());
        list.add(new Porthos());
        list.add(new Athos());
        list.add(new DArtagnan());
        return list;
    }

    public MusketeersVow() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE,4);
        ExhaustiveVariable.setBaseValue(this, 1);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.upgrade(this, 1);
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
                this.cardsToPreview =  returnChoose().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoose().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("MusketeersVow_EH"));
        addToBot(new MusketeersAction(true,true));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("MusketeersVow"));
        addToBot(new MusketeersAction(true,false));
    }


    @Override
    public AbstractCard makeCopy() {
        return new MusketeersVow();
    }
}

