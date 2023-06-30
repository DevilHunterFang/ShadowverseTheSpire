package shadowverse.cards.Witch.Spellboost1;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.CelestialShikigami;
import shadowverse.cards.Neutral.Temp.DemonicShikigami;
import shadowverse.cards.Neutral.Temp.NobleShikigami;
import shadowverse.cards.Neutral.Temp.PaperShikigami;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;
import java.util.List;


public class Kuon
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Kuon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kuon");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kuon2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Kuon.png";
    public static final String IMG_PATH2 = "img/cards/Kuon2.png";
    public static final int BASE_COST = 9;
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;

    public static ArrayList<AbstractCard> returnShikigami() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new PaperShikigami());
        list.add(new DemonicShikigami());
        list.add(new CelestialShikigami());
        return list;
    }

    public static ArrayList<AbstractCard> returnShikigami2() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new PaperShikigami());
        list.add(new DemonicShikigami());
        list.add(new CelestialShikigami());
        list.add(new NobleShikigami());
        return list;
    }

    public Kuon() {
        super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
        this.baseBlock = 10;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (this.chosenBranch() == 0) {
            if (c.type == CardType.SKILL) {
                flash();
                addToBot(new SFXAction("spell_boost"));
                addToBot(new ReduceCostAction((AbstractCard) this));
            }
        }else {
            if (c.type == CardType.SKILL) {
                flash();
                this.magicNumber = ++this.baseMagicNumber;
                addToBot(new SFXAction("spell_boost"));
            }
        }
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    public void update() {
        super.update();
        switch (previewBranch){
            case 0:
                if (this.hb.hovered)
                    if (this.rotationTimer <= 0.0F) {
                        this.rotationTimer = 2.0F;
                        this.cardsToPreview = (AbstractCard) returnShikigami().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnShikigami().size() - 1) {
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
                        this.cardsToPreview = (AbstractCard) returnShikigami2().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnShikigami2().size() - 1) {
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

    public void applyPowers() {
        super.applyPowers();
        if (chosenBranch()==1){
            this.rawDescription = cardStrings2.DESCRIPTION;
            this.rawDescription += cardStrings2.EXTENDED_DESCRIPTION[0] + this.magicNumber;
            this.rawDescription += cardStrings2.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new HeartBuffEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
        switch (chosenBranch()){
            case 0:
                addToBot(new SFXAction("Kuon"));
                ArrayList<AbstractCard> shikigamiHand = returnShikigami();
                for (AbstractCard c : shikigamiHand) {
                    if (this.upgraded) {
                        c.upgrade();
                    }
                    addToBot(new MakeTempCardInHandAction(c, 1));
                }
                this.cost = BASE_COST;
                break;
            case 1:
                addToBot(new SFXAction("Kuon2"));
                if (this.magicNumber>=9){
                    addToBot(new MakeTempCardInHandAction(new CelestialShikigami()));
                    addToBot(new MakeTempCardInHandAction(new NobleShikigami()));
                    addToBot(new GainEnergyAction(1));
                } else if (this.magicNumber>=6) {
                    addToBot(new MakeTempCardInHandAction(new CelestialShikigami()));
                    addToBot(new MakeTempCardInHandAction(new DemonicShikigami()));
                    addToBot(new GainEnergyAction(1));
                }else if (this.magicNumber>=3){
                    addToBot(new MakeTempCardInHandAction(new PaperShikigami()));
                    addToBot(new MakeTempCardInHandAction(new DemonicShikigami()));
                    addToBot(new GainEnergyAction(1));
                }
                this.baseMagicNumber = 0;
                this.magicNumber = this.baseMagicNumber;
                break;
            default:
                break;
        }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Kuon.this.timesUpgraded;
                Kuon.this.upgraded = true;
                Kuon.this.name = NAME + "+";
                Kuon.this.initializeTitle();
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
                Kuon.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Kuon.this.timesUpgraded;
                Kuon.this.upgraded = true;
                Kuon.this.textureImg = IMG_PATH2;
                Kuon.this.loadCardImage(IMG_PATH2);
                Kuon.this.name = cardStrings2.NAME;
                Kuon.this.initializeTitle();
                Kuon.this.upgradeBaseCost(2);
                Kuon.this.rawDescription = cardStrings2.DESCRIPTION;
                Kuon.this.initializeDescription();
                Kuon.this.baseBlock = 8;
                Kuon.this.upgradedBlock = true;
                Kuon.this.tags.remove(AbstractShadowversePlayer.Enums.SPELL_BOOST);
                Kuon.this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
                Kuon.this.previewBranch = 1;
            }
        });
        return list;
    }
}

