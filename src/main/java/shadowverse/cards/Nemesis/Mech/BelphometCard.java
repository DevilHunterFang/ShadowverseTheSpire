package shadowverse.cards.Nemesis.Mech;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.TagFusionAction;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;
import java.util.List;

public class BelphometCard extends AbstractRightClickCard2 implements BranchableUpgradeCard{
    public static final String ID = "shadowverse:BelphometCard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BelphometCard");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:BelphometCard2");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:BelphometCard3");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BelphometCard.png";
    public static final String IMG_PATH2 = "img/cards/BelphometCard2.png";
    public static final String IMG_PATH3 = "img/cards/BelphometCard3.png";
    private boolean hasFusion = false;
    private boolean hasGenerate = false;
    private int turnCount = 0;
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;


    public static ArrayList<AbstractCard> returnElinese(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new TisiphoneCard());
        list.add(new AlectorCard());
        list.add(new MegaeraCard());
        return list;
    }

    public static ArrayList<AbstractCard> returnNeoElinese(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new NeoTisiphone());
        list.add(new NeoAlector());
        list.add(new NeoMegaera());
        list.add(new ArmoredTentacle());
        list.add(new AssaultTentacle());
        return list;
    }

    public static ArrayList<AbstractCard> returnTentacle(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ArmoredTentacle());
        list.add(new AssaultTentacle());
        return list;
    }

    public static AbstractCard returnRandomElinese(Random rng) {
        return returnElinese().get(rng.random(returnElinese().size() - 1));
    }

    public BelphometCard() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.baseBlock = 12;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    public void update() {
        super.update();
            switch (previewBranch){
                case 0:
                default:
                    if (this.hb.hovered)
                        if (this.rotationTimer <= 0.0F) {
                            this.rotationTimer = 2.0F;
                            this.cardsToPreview = (AbstractCard)returnElinese().get(previewIndex).makeCopy();
                            if (this.previewIndex == returnElinese().size() - 1) {
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
                            this.cardsToPreview = (AbstractCard)returnTentacle().get(previewIndex).makeCopy();
                            if (this.previewIndex == returnTentacle().size() - 1) {
                                this.previewIndex = 0;
                            } else {
                                this.previewIndex++;
                            }
                        } else {
                            this.rotationTimer -= Gdx.graphics.getDeltaTime();
                        }
                    break;
                case 2:
                    if (this.hb.hovered)
                        if (this.rotationTimer <= 0.0F) {
                            this.rotationTimer = 2.0F;
                            this.cardsToPreview = (AbstractCard)returnNeoElinese().get(previewIndex).makeCopy();
                            if (this.previewIndex == returnNeoElinese().size() - 1) {
                                this.previewIndex = 0;
                            } else {
                                this.previewIndex++;
                            }
                        } else {
                            this.rotationTimer -= Gdx.graphics.getDeltaTime();
                        }
            }
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++BelphometCard.this.timesUpgraded;
                BelphometCard.this.upgraded = true;
                BelphometCard.this.name = NAME + "+";
                BelphometCard.this.initializeTitle();
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
                BelphometCard.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++BelphometCard.this.timesUpgraded;
                BelphometCard.this.upgraded = true;
                BelphometCard.this.textureImg = IMG_PATH2;
                BelphometCard.this.loadCardImage(IMG_PATH2);
                BelphometCard.this.name = cardStrings2.NAME;
                BelphometCard.this.initializeTitle();
                BelphometCard.this.upgradeBaseCost(3);
                BelphometCard.this.rawDescription = cardStrings2.DESCRIPTION;
                BelphometCard.this.initializeDescription();
                BelphometCard.this.previewBranch = 1;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++BelphometCard.this.timesUpgraded;
                BelphometCard.this.upgraded = true;
                BelphometCard.this.textureImg = IMG_PATH3;
                BelphometCard.this.loadCardImage(IMG_PATH3);
                BelphometCard.this.name = cardStrings3.NAME;
                BelphometCard.this.initializeTitle();
                BelphometCard.this.upgradeBaseCost(4);
                BelphometCard.this.rawDescription = cardStrings3.DESCRIPTION;
                BelphometCard.this.initializeDescription();
                BelphometCard.this.previewBranch = 2;
            }
        });
        return list;
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.ROYAL, true)));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.SKY.cpy(),Color.WHITE.cpy(),"HEAL_3")));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.2F));
        if (!this.upgraded){
            addToBot((AbstractGameAction)new SFXAction("BelphometCard"));
            for (AbstractCard c:abstractPlayer.drawPile.group){
                if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                    addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.drawPile));
                    addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(returnRandomElinese(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy(),1,true,true));
                }
            }
        }else {
            switch (chosenBranch()){
                case 0:
                    addToBot((AbstractGameAction)new SFXAction("BelphometCard"));
                    for (AbstractCard c:abstractPlayer.drawPile.group){
                        if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)){
                            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.drawPile));
                            AbstractCard elinese = returnRandomElinese(AbstractDungeon.cardRandomRng);
                            elinese.upgrade();
                            addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(elinese.makeStatEquivalentCopy(),1,true,true));
                        }
                    }
                    break;
                case 1:
                    addToBot((AbstractGameAction)new SFXAction("Belphomet3"));
                    AbstractCard assault = (AbstractCard)new AssaultTentacle();
                    AbstractCard armored = (AbstractCard)new ArmoredTentacle();
                    int [] l = new int[3];
                    rand(l,3,this.magicNumber);
                    int x = l[0];
                    int y = l[1];
                    int z = l[2];
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(assault.makeStatEquivalentCopy(),x));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(armored.makeStatEquivalentCopy(),y));
                    if (z>0){
                        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DrawCardNextTurnPower(abstractPlayer,z*3),z*3));
                        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,z*3),z*3));
                        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DexterityPower(abstractPlayer,z*3),z*3));
                    }
                    this.baseMagicNumber = 0;
                    this.magicNumber = this.baseMagicNumber;
                    break;
                case 2:
                    addToBot((AbstractGameAction)new SFXAction("UltimateCreator"));
                    addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NeoTisiphone().makeStatEquivalentCopy()));
                    if (this.magicNumber>0)
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NeoAlector().makeStatEquivalentCopy()));
                    if (this.magicNumber>1)
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NeoMegaera().makeStatEquivalentCopy()));
                    this.baseMagicNumber = 0;
                    this.magicNumber = this.baseMagicNumber;
                    break;
                default:
                    break;
            }
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = this.magicNumber;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void atTurnStart(){
        hasFusion = false;
        hasGenerate = false;
    }

    @Override
    protected void onRightClick() {
        if (!this.upgraded){
            return;
        }else {
            switch (chosenBranch()){
                case 0:
                    return;
                case 1:
                    addToBot((AbstractGameAction)new TagFusionAction(8,false,true,true,this,this.hasFusion,AbstractShadowversePlayer.Enums.MACHINE));
                    if (!this.hasFusion){
                        turnCount++;
                        hasFusion = true;
                    }
                    break;
                case 2:
                    addToBot((AbstractGameAction)new TagFusionAction(8,false,true,true,this,this.hasFusion,AbstractShadowversePlayer.Enums.MACHINE));
                    if (!this.hasFusion){
                        turnCount++;
                        hasFusion = true;
                        AbstractCard assault = (AbstractCard)new AssaultTentacle();
                        AbstractCard armored = (AbstractCard)new ArmoredTentacle();
                        switch (turnCount){
                            case 1:
                                break;
                            case 2:
                            case 4:
                                if (EnergyPanel.getCurrentEnergy()>1&&!hasGenerate){
                                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(armored.makeStatEquivalentCopy()));
                                    EnergyPanel.useEnergy(1);
                                    hasGenerate = true;
                                }
                                break;
                            case 3:
                            case 5:
                                if (EnergyPanel.getCurrentEnergy()>1&&!hasGenerate){
                                    addToBot((AbstractGameAction)new MakeTempCardInHandAction(assault.makeStatEquivalentCopy()));
                                    EnergyPanel.useEnergy(1);
                                    hasGenerate = true;
                                }
                                break;
                            default:
                                turnCount = 0;
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void rand(int[] l, int n, int m)
    {
        int i;
        for(i=0;i<n-1;i++)
        {
            l[i] = AbstractDungeon.cardRandomRng.random(2 * m / (n - i));
            m -= l[i];
        }
        l[i] = m;
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BelphometCard();
    }
}
