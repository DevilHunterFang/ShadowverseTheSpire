package shadowverse.cards.Witch.Spellboost1;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import rs.lazymankits.actions.common.DrawExptCardAction;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.SpellBoostAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;
import java.util.List;

public class DimensionalWitch extends CustomCard implements BranchableUpgradeCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DimensionalWitch");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:DimensionalWitch2");
    public static final String ID = "shadowverse:DimensionalWitch";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DimensionalWitch.png";
    public static final String IMG_PATH2 = "img/cards/DimensionalWitch2.png";
    public static final int BASE_COST = 6;

    public DimensionalWitch() {
        super("shadowverse:DimensionalWitch", NAME, "img/cards/DimensionalWitch.png", 6, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 12;
        this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.SKILL) {
            flash();
            addToBot((AbstractGameAction) new SFXAction("spell_boost"));
            addToBot((AbstractGameAction) new ReduceCostAction((AbstractCard) this));
        }
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("DimensionalWitch"));
                addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                this.cost = 6;
                int count = AbstractDungeon.player.hand.size();
                for (int i = 0; i < count; i++) {
                    if (Settings.FAST_MODE) {
                        addToTop((AbstractGameAction) new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
                    } else {
                        addToTop((AbstractGameAction) new ExhaustAction(1, true, true));
                    }
                }
                addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) abstractPlayer, 5));
                addToBot((AbstractGameAction) new SpellBoostAction(abstractPlayer, (AbstractCard) this, abstractPlayer.hand.group));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("DimensionalWitch2"));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new RainbowCardEffect()));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                int size = 10-abstractPlayer.hand.group.size();
                addToBot((AbstractGameAction)new DrawExptCardAction((AbstractCreature)abstractPlayer, size, c -> (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)||c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)), new AbstractGameAction() {
                    public void update() {
                        this.isDone = true;
                        for (AbstractCard c : DrawCardAction.drawnCards) {
                            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
                                for (int i = 0; i < DimensionalWitch.this.magicNumber; i++) {
                                    c.flash();
                                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                                    addToBot((AbstractGameAction)new ReduceCostAction(c));
                                }
                            }
                            if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
                                for (int i = 0; i < DimensionalWitch.this.magicNumber; i++) {
                                    c.flash();
                                    c.magicNumber = ++c.baseMagicNumber;
                                    addToBot((AbstractGameAction)new SFXAction("spell_boost"));
                                }
                            }
                        }
                    }
                }));
                addToBot((AbstractGameAction)new DrawCardAction(1));
                this.cost = 6;
                break;
            default:
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DimensionalWitch();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++DimensionalWitch.this.timesUpgraded;
                DimensionalWitch.this.upgraded = true;
                DimensionalWitch.this.name = NAME + "+";
                DimensionalWitch.this.initializeTitle();
                DimensionalWitch.this.baseBlock = 16;
                DimensionalWitch.this.upgradedBlock = true;
                DimensionalWitch.this.baseMagicNumber = 5;
                DimensionalWitch.this.magicNumber = DimensionalWitch.this.baseMagicNumber;
                DimensionalWitch.this.upgradedMagicNumber = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++DimensionalWitch.this.timesUpgraded;
                DimensionalWitch.this.upgraded = true;
                DimensionalWitch.this.textureImg = IMG_PATH2;
                DimensionalWitch.this.loadCardImage(IMG_PATH2);
                DimensionalWitch.this.name = cardStrings2.NAME;
                DimensionalWitch.this.initializeTitle();
                DimensionalWitch.this.rawDescription = cardStrings2.DESCRIPTION;
                DimensionalWitch.this.initializeDescription();
                DimensionalWitch.this.baseBlock = 16;
                DimensionalWitch.this.upgradedBlock = true;
                DimensionalWitch.this.baseMagicNumber = 3;
                DimensionalWitch.this.magicNumber = DimensionalWitch.this.baseMagicNumber;
                DimensionalWitch.this.upgradedMagicNumber = true;
            }
        });

        return list;
    }
}

