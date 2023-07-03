package shadowverse.cards.Vampire.Bat;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.VanpiPower;
import shadowverse.powers.WrathPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;
import java.util.List;


public class Vanpi
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Vanpi";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vanpi");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vanpi_Q");
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Vanpi.png";
    public static final String IMG_PATH2 = "img/cards/Vanpi_Q.png";
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ForestBat());
        list.add(new Vanpi1());
        list.add(new Vanpi2());
        return list;
    }

    public static ArrayList<AbstractCard> returnChoice2() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ForestBat());
        list.add(new SummoningBloodkin());
        return list;
    }

    public Vanpi() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 18;
    }

    public void update() {
        super.update();
        switch (previewBranch) {
            case 0:
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
                break;
            case 1:
                if (this.hb.hovered)
                    if (this.rotationTimer <= 0.0F) {
                        this.rotationTimer = 2.0F;
                        this.cardsToPreview = returnChoice2().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnChoice2().size() - 1) {
                            this.previewIndex = 0;
                        } else {
                            this.previewIndex++;
                        }
                    } else {
                        this.rotationTimer -= Gdx.graphics.getDeltaTime();
                    }
                break;
            default:
                break;
        }
    }

    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                if (p.hasPower(EpitaphPower.POWER_ID) || p.stance.ID.equals(Vengeance.STANCE_ID)) {
                    AbstractCard v2 = new Vanpi2();
                    AbstractCard v1 = new Vanpi1();
                    if (this.upgraded) {
                        v1.upgrade();
                        v2.upgrade();
                    }
                    addToBot(new ChoiceAction(new AbstractCard[]{v2, v1}));
                } else {
                    addToBot(new SFXAction("Vanpi"));
                    addToBot(new GainBlockAction(p, this.block));
                    addToBot(new LoseHPAction(p, p, 2));
                    for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                        if (!mo.isDeadOrEscaped()) {
                            addToBot(new LoseHPAction(mo, p, 2));
                        }
                    }
                    AbstractCard fb = new ForestBat();
                    if (this.upgraded)
                        fb.upgrade();
                    addToBot(new MakeTempCardInHandAction(fb));
                }
                break;
            case 1:
                addToBot(new SFXAction("Vanpi_Q"));
                addToBot(new GainBlockAction(p, this.block));
                addToBot(new LoseHPAction(p,p,1));
                addToBot(new MakeTempCardInHandAction(new ForestBat()));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()){
                        addToBot(new LoseHPAction(mo,p,1));
                        addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, 1), 1));
                        addToBot(new ApplyPowerAction(mo, p, new LoseStrengthPower(mo, 1), 1));
                    }
                }
                if (p.hasPower(EpitaphPower.POWER_ID) || p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(WrathPower.POWER_ID)) {
                    addToBot(new ApplyPowerAction(p,p,new VanpiPower(p)));
                    AbstractCard tmp = new SummoningBloodkin();
                    tmp.exhaustOnUseOnce = true;
                    tmp.exhaust = true;
                    tmp.rawDescription += " NL "+TEXT+" 。";
                    tmp.initializeDescription();
                    tmp.applyPowers();
                    p.hand.addToTop(tmp);
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return new Vanpi();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Vanpi.this.timesUpgraded;
                Vanpi.this.upgraded = true;
                Vanpi.this.name = NAME + "+";
                Vanpi.this.initializeTitle();
                Vanpi.this.baseBlock = 21;
                Vanpi.this.upgradedBlock = true;
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
                Vanpi.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Vanpi.this.timesUpgraded;
                Vanpi.this.upgraded = true;
                Vanpi.this.textureImg = IMG_PATH2;
                Vanpi.this.loadCardImage(IMG_PATH2);
                Vanpi.this.name = cardStrings2.NAME;
                Vanpi.this.initializeTitle();
                Vanpi.this.upgradeBaseCost(1);
                Vanpi.this.baseBlock = 12;
                Vanpi.this.upgradedBlock = true;
                Vanpi.this.rawDescription = cardStrings2.DESCRIPTION;
                Vanpi.this.initializeDescription();
                Vanpi.this.previewBranch = 1;
            }
        });
        return list;
    }
}

