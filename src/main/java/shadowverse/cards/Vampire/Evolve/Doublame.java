package shadowverse.cards.Vampire.Evolve;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.Leoparion;
import shadowverse.cards.Neutral.Temp.PeckishOwlcat;
import shadowverse.characters.Vampire;

import java.util.ArrayList;


public class Doublame extends CustomCard {
    public static final String ID = "shadowverse:Doublame";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Doublame");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Doublame.png";

    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Leoparion(0));
        list.add(new PeckishOwlcat(0));
        return list;
    }

    public Doublame() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 4;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Doublame"));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        int amount = 0;
        for (AbstractCard c : abstractPlayer.exhaustPile.group){
            if (c instanceof EvolutionPoint)
                amount++;
        }
        if (amount >= 5){
            addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
        }
        AbstractCard l = new Leoparion(amount);
        AbstractCard p = new PeckishOwlcat(amount);
        addToBot(new ChoiceAction2(l,p));
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

    public AbstractCard makeCopy() {
        return (AbstractCard) new Doublame();
    }

}

