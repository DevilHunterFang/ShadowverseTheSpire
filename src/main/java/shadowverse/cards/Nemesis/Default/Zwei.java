package shadowverse.cards.Nemesis.Default;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.List;


public class Zwei extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Zwei";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zwei");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Zwei2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Zwei.png";
    public static final String IMG_PATH2 = "img/cards/Zwei2.png";
    public static AbstractCard token = new Victoria();
    private boolean branch2 = false;
    private float rotationTimer;
    private int previewIndex;
    public boolean doubleCheck = false;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Victoria());
        list.add(new Puppet());
        return list;
    }

    public Zwei() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 16;
    }

    public void update() {
        super.update();
        if (branch2) {
            if (this.hb.hovered)
                if (this.rotationTimer <= 0.0F) {
                    this.rotationTimer = 2.0F;
                    this.cardsToPreview = (AbstractCard) returnProphecy().get(previewIndex).makeCopy();
                    if (this.previewIndex == returnProphecy().size() - 1) {
                        this.previewIndex = 0;
                    } else {
                        this.previewIndex++;
                    }
                } else {
                    this.rotationTimer -= Gdx.graphics.getDeltaTime();
                }
        } else {
            AbstractCard c = this.token.makeCopy();
            if (this.upgraded)
                c.upgrade();
            this.cardsToPreview = c;
        }
    }

    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (this.chosenBranch()==1){
            if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
                doubleCheck = true;
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(1);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            } else {
                if (doubleCheck) {
                    doubleCheck = false;
                } else {
                    if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                        setCostForTurn(1);
                        this.type = CardType.SKILL;
                        applyPowers();
                    }
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (this.chosenBranch()==1){
            if (EnergyPanel.getCurrentEnergy() >= 2 && this.type != CardType.ATTACK) {
                resetAttributes();
                this.type = CardType.ATTACK;
                applyPowers();
            }
        }
    }

    public void triggerWhenDrawn() {
        if (this.chosenBranch()==1){
            if (Shadowverse.Accelerate((AbstractCard) this)) {
                super.triggerWhenDrawn();
                setCostForTurn(1);
                this.type = CardType.SKILL;
            } else {
                this.type = CardType.ATTACK;
            }
            applyPowers();
        }
    }

    @Override
    public void atTurnStart() {
        if (this.chosenBranch()==1){
            if (AbstractDungeon.player.hand.group.contains(this)) {
                if (EnergyPanel.getCurrentEnergy() < 2) {
                    setCostForTurn(1);
                    this.type = CardType.SKILL;
                } else {
                    resetAttributes();
                    this.type = CardType.ATTACK;
                }
                applyPowers();
            }
        }
    }

    public void onMoveToDiscard() {
        if (this.chosenBranch()==1){
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Zwei"));
                addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                AbstractCard v = (AbstractCard) new Victoria();
                if (this.upgraded)
                    v.upgrade();
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(v));
                break;
            case 1:
                if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
                    addToBot((AbstractGameAction)new SFXAction("Zwei2_Acc"));
                }else {
                    addToBot((AbstractGameAction) new SFXAction("Zwei2"));
                    addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                    AbstractCard v2 = (AbstractCard) new Victoria();
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(v2));
                }
                addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Puppet(),2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Zwei();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Zwei.this.timesUpgraded;
                Zwei.this.upgraded = true;
                Zwei.this.name = NAME + "+";
                Zwei.this.initializeTitle();
                Zwei.this.baseBlock = 20;
                Zwei.this.upgradedBlock = true;
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Zwei.this.timesUpgraded;
                Zwei.this.upgraded = true;
                Zwei.this.textureImg = IMG_PATH2;
                Zwei.this.loadCardImage(IMG_PATH2);
                Zwei.this.name = cardStrings2.NAME;
                Zwei.this.initializeTitle();
                Zwei.this.upgradeBaseCost(2);
                Zwei.this.baseBlock = 10;
                Zwei.this.upgradedBlock = true;
                Zwei.this.rawDescription = cardStrings2.DESCRIPTION;
                Zwei.this.initializeDescription();
                Zwei.this.branch2 = true;
                Zwei.this.rarity = CardRarity.RARE;
                Zwei.this.setDisplayRarity(Zwei.this.rarity);
            }
        });
        return list;
    }

    @Override
    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (this.chosenBranch()==1&&Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            card = (new Zwei2_Acc()).makeStatEquivalentCopy();
            card.uuid = (new Zwei2_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }
}

