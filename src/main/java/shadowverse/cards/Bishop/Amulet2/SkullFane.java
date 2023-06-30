package shadowverse.cards.Bishop.Amulet2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.SkullFaneAccAction;
import shadowverse.action.SkullFaneAction;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;
import java.util.List;

public class SkullFane
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:SkullFane";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SkullFane");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SkullFane.png";
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:SkullFane2");
    public static final String IMG_PATH2 = "img/cards/SkullFane2.png";
    private int previewBranch;
    private boolean hasReduced;

    public SkullFane() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 4;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void update() {
        if (previewBranch == 1) {
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                    Shadowverse.Accelerate(this)) {
                setCostForTurn(0);
                this.type = CardType.SKILL;
            } else {
                if (this.type == CardType.SKILL) {
                    if (hasReduced) {
                        this.costForTurn = 2;
                        if (this.cost != this.costForTurn) {
                            this.isCostModified = true;
                        }
                        this.cost = this.costForTurn;
                    } else {
                        setCostForTurn(5);
                    }
                    this.type = CardType.ATTACK;
                }
            }
        }
        super.update();
    }

    public void triggerOnGlowCheck() {
        if (previewBranch == 1 && Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (this.previewBranch == 1)
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
                if (((AbstractShadowversePlayer) AbstractDungeon.player).skullFaneAccCount >= 4) {
                    this.type = CardType.ATTACK;
                    if (!hasReduced) {
                        this.costForTurn = 2;
                        if (this.cost != this.costForTurn) {
                            this.isCostModified = true;
                        }
                        this.cost = this.costForTurn;
                        hasReduced = true;
                    } else {
                        this.costForTurn = 0;
                        if (this.cost != this.costForTurn) {
                            this.isCostModified = true;
                        }
                        this.cost = this.costForTurn;
                    }

                }
            }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (previewBranch == 0)
            if (c instanceof AbstractAmuletCard || c instanceof AbstractNoCountDownAmulet || (c instanceof AbstractCrystalizeCard && c.type == CardType.POWER)) {
                addToBot(new ReduceCostAction(this));
            }
    }

    @Override
    public void triggerWhenDrawn() {
        if (previewBranch == 0)
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof AmuletOrb) {
                    if (((AmuletOrb) o).amulet.type == CardType.CURSE)
                        addToBot(new ReduceCostForTurnAction(this, 1));
                }
            }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()) {
            case 0:
                addToBot(new SFXAction("SkullFane"));
                addToBot(new VFXAction(new HeartMegaDebuffEffect()));
                addToBot(new SkullFaneAction(this.magicNumber, this.block));
                break;
            case 1:
                if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
                    addToBot(new SFXAction("SkullFane2_Acc"));
                    addToBot(new SkullFaneAccAction(this));
                } else {
                    addToBot(new SFXAction("SkullFane2"));
                    addToBot(new VFXAction(new HeartMegaDebuffEffect()));
                    addToBot(new SkullFaneAction(this.magicNumber, this.block));
                    addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
                }
                break;
            default:
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SkullFane();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++SkullFane.this.timesUpgraded;
                SkullFane.this.upgraded = true;
                SkullFane.this.name = NAME + "+";
                SkullFane.this.initializeTitle();
                SkullFane.this.baseBlock = 6;
                SkullFane.this.upgradedBlock = true;
                SkullFane.this.baseMagicNumber = 2;
                SkullFane.this.magicNumber = SkullFane.this.baseMagicNumber;
                SkullFane.this.upgradedMagicNumber = true;
                SkullFane.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++SkullFane.this.timesUpgraded;
                SkullFane.this.upgraded = true;
                SkullFane.this.baseDamage = 20;
                SkullFane.this.upgradedDamage = true;
                SkullFane.this.textureImg = IMG_PATH2;
                SkullFane.this.loadCardImage(IMG_PATH2);
                SkullFane.this.name = cardStrings2.NAME;
                SkullFane.this.initializeTitle();
                SkullFane.this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
                SkullFane.this.rawDescription = cardStrings2.DESCRIPTION;
                SkullFane.this.initializeDescription();
                SkullFane.this.previewBranch = 1;
            }
        });
        return list;
    }
}


