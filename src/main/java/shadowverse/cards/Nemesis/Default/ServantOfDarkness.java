package shadowverse.cards.Nemesis.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class ServantOfDarkness
        extends CustomCard {
    public static final String ID = "shadowverse:ServantOfDarkness";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ServantOfDarkness");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ServantOfDarkness.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GuardFormGolem());
        list.add(new StrikeFormGolem());
        return list;
    }

    public ServantOfDarkness() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ALL);
        this.baseBlock = 3;
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview =  returnChoice().get(previewIndex).makeCopy();
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
            upgradeBlock(1);
            upgradeBaseCost(0);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("ServantOfDarkness"));
        addToBot( new GainBlockAction(abstractPlayer, this.block));
        AbstractCard g = new GuardFormGolem();
        g.cost = 0;
        g.costForTurn = 0;
        g.isCostModified = true;
        g.applyPowers();
        abstractPlayer.hand.addToTop(g);
        if (this.upgraded){
            AbstractCard s = new StrikeFormGolem();
            s.cost = 0;
            s.costForTurn = 0;
            s.isCostModified = true;
            s.applyPowers();
            abstractPlayer.hand.addToTop(s);
        }
    }


    public AbstractCard makeCopy() {
        return  new ServantOfDarkness();
    }
}

