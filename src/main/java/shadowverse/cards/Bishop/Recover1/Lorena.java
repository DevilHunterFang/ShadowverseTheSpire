package shadowverse.cards.Bishop.Recover1;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Bishop;

import java.util.ArrayList;
import java.util.List;


public class Lorena extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Lorena";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lorena");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lorena2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lorena.png";
    public static final String IMG_PATH2 = "img/cards/Lorena2.png";
    private boolean branch1 = true;
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new LorenaWater());
        list.add(new LorenaPunch());
        return list;
    }


    public Lorena() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
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
                if (this.upgraded && this.branch1)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Lorena"));
                addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
                AbstractCard water = new LorenaWater();
                AbstractCard punch = new LorenaPunch();
                if (this.upgraded){
                    water.upgrade();
                    punch.upgrade();
                }
                addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { water, punch }));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Lorena2"));
                addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(new EvolutionPoint()));
                AbstractCard water2 = new LorenaWater();
                AbstractCard punch2 = new LorenaPunch();
                punch2.setCostForTurn(0);
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(water2));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(punch2));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lorena();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Lorena.this.timesUpgraded;
                Lorena.this.upgraded = true;
                Lorena.this.name = NAME + "+";
                Lorena.this.initializeTitle();
                Lorena.this.baseBlock = 9;
                Lorena.this.upgradedBlock = true;
                Lorena.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Lorena.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Lorena.this.timesUpgraded;
                Lorena.this.upgraded = true;
                Lorena.this.textureImg = IMG_PATH2;
                Lorena.this.loadCardImage(IMG_PATH2);
                Lorena.this.name = cardStrings2.NAME;
                Lorena.this.initializeTitle();
                Lorena.this.rawDescription = cardStrings2.DESCRIPTION;
                Lorena.this.initializeDescription();
                Lorena.this.branch1 = false;
            }
        });
        return list;
    }
}

