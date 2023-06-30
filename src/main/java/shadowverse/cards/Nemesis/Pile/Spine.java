package shadowverse.cards.Nemesis.Pile;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.Choose2DifferentAction;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.SpinePower;

import java.util.ArrayList;
import java.util.List;

public class Spine extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Spine";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Spine");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Spine2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Spine.png";
    public static final String IMG_PATH2 = "img/cards/Spine2.png";
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;
    public boolean doubleCheck = false;

    public static AbstractCard token = new SpineArtifact();

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new BlitzArtifact());
        list.add(new EdgeArtifact());
        list.add(new ProtectArtifact());
        return list;
    }


    public Spine() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 15;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (this.chosenBranch() == 1) {
            if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
                doubleCheck = true;
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(0);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            } else {
                if (doubleCheck) {
                    doubleCheck = false;
                } else {
                    if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                        setCostForTurn(0);
                        this.type = CardType.SKILL;
                        applyPowers();
                    }
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (this.chosenBranch() == 1) {
            if (EnergyPanel.getCurrentEnergy() >= 2 && this.type != CardType.ATTACK) {
                resetAttributes();
                this.type = CardType.ATTACK;
                applyPowers();
            }
        }
    }

    public void triggerWhenDrawn() {
        if (this.chosenBranch() == 1) {
            if (Shadowverse.Accelerate((AbstractCard) this)) {
                super.triggerWhenDrawn();
                setCostForTurn(0);
                this.type = CardType.SKILL;
            } else {
                this.type = CardType.ATTACK;
            }
            applyPowers();
        }
    }

    @Override
    public void atTurnStart() {
        if (this.chosenBranch() == 1) {
            if (AbstractDungeon.player.hand.group.contains(this)) {
                if (EnergyPanel.getCurrentEnergy() < 2) {
                    setCostForTurn(0);
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
        if (this.chosenBranch() == 1) {
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void update() {
        super.update();
        switch (previewBranch) {
            case 0:
            default:
                if (this.hb.hovered)
                    this.cardsToPreview = this.token.makeCopy();
                break;
            case 1:
                if (this.hb.hovered)
                    if (this.rotationTimer <= 0.0F) {
                        this.rotationTimer = 2.0F;
                        this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnChoice().size() - 1) {
                            this.previewIndex = 0;
                        } else {
                            this.previewIndex++;
                        }
                    } else {
                        this.rotationTimer -= Gdx.graphics.getDeltaTime();
                    }
                break;
        }
    }

    public void triggerOnGlowCheck() {
        if (this.chosenBranch() == 1 && Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Spine.this.timesUpgraded;
                Spine.this.upgraded = true;
                Spine.this.name = NAME + "+";
                Spine.this.initializeTitle();
                Spine.this.baseBlock = 20;
                Spine.this.upgradedBlock = true;
                Spine.this.baseMagicNumber = 2;
                Spine.this.magicNumber = 2;
                Spine.this.upgradedMagicNumber = true;
                Spine.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Spine.this.timesUpgraded;
                Spine.this.upgraded = true;
                Spine.this.textureImg = IMG_PATH2;
                Spine.this.loadCardImage(IMG_PATH2);
                Spine.this.name = cardStrings2.NAME;
                Spine.this.initializeTitle();
                Spine.this.baseBlock = 2;
                Spine.this.upgradedBlock = true;
                Spine.this.exhaust = true;
                Spine.this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
                Spine.this.rawDescription = cardStrings2.DESCRIPTION;
                Spine.this.initializeDescription();
                Spine.this.previewBranch = 1;
            }
        });
        return list;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.2F));
        if (!this.upgraded) {
            addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
            addToBot((AbstractGameAction) new SFXAction("Spine"));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.token.makeStatEquivalentCopy()));
            addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new SpinePower(abstractPlayer, this.magicNumber), this.magicNumber));
        } else {
            switch (chosenBranch()) {
                case 0:
                    addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                    addToBot((AbstractGameAction) new SFXAction("Spine"));
                    addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.token.makeStatEquivalentCopy()));
                    addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new SpinePower(abstractPlayer, this.magicNumber), this.magicNumber));
                    break;
                case 1:
                    if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
                        addToBot((AbstractGameAction) new SFXAction("Spine2_Acc"));
                        addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.token.makeStatEquivalentCopy()));
                    } else {
                        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
                        addToBot((AbstractGameAction) new SFXAction("Spine2"));
                        ArrayList<AbstractCard> list = new ArrayList<>();
                        ArrayList<String> dup = new ArrayList<>();
                        for (AbstractCard c : abstractPlayer.exhaustPile.group) {
                            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && !dup.contains(c.cardID)) {
                                dup.add(c.cardID);
                                AbstractCard card = c.makeCopy();
                                list.add(card);
                            }
                        }
                        if (list.size() >= 6) {
                            for (AbstractCard c : returnChoice()) {
                                AbstractCard tmp = c.makeStatEquivalentCopy();
                                tmp.setCostForTurn(0);
                                addToBot((AbstractGameAction) new MakeTempCardInHandAction(tmp));
                            }
                        } else {
                            AbstractCard[] artifactToken = new AbstractCard[returnChoice().size()];
                            returnChoice().toArray(artifactToken);
                            for (AbstractCard c : artifactToken) {
                                c.setCostForTurn(0);
                            }
                            addToBot((AbstractGameAction) new Choose2DifferentAction(true, artifactToken));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (this.chosenBranch() == 1 && Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            card = (new Spine_Acc()).makeStatEquivalentCopy();
            card.uuid = (new Spine_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }


    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard) new Spine();
    }
}
