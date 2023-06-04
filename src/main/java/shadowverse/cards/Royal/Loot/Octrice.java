package shadowverse.cards.Royal.Loot;

import basemod.abstracts.CustomCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverseCharbosses.powers.cardpowers.EnemyFlameBarrierPower;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.ErikaOrb;
import shadowverse.orbs.Minion;
import shadowverse.powers.BetterFlightPower;
import shadowverse.powers.CurseOfGeass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Octrice extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Octrice";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Octrice");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Octrice2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Octrice.png";
    public static final String IMG_PATH2 = "img/cards/Octrice2.png";
    public static final String IMG_PATH_EV = "img/cards/Octrice_Ev.png";
    private float rotationTimer;
    private int previewIndex;
    private int previewBranch;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        return list;
    }

    public static ArrayList<AbstractCard> returnProphecy2() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        list.add(new LightOfHollow());
        return list;
    }

    public Octrice() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.tags.add(CardTags.HEALING);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void update() {
        super.update();
        switch (previewBranch) {
            case 0:
                if (this.hb.hovered && this.upgraded) {
                    if (this.rotationTimer <= 0.0F) {
                        this.rotationTimer = 2.0F;
                        this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnProphecy().size() - 1) {
                            this.previewIndex = 0;
                        } else {
                            this.previewIndex++;
                        }
                    } else {
                        this.rotationTimer -= Gdx.graphics.getDeltaTime();
                    }
                }
                break;
            case 1:
                if (this.hb.hovered && this.upgraded) {
                    if (this.rotationTimer <= 0.0F) {
                        this.rotationTimer = 2.0F;
                        this.cardsToPreview = returnProphecy2().get(previewIndex).makeCopy();
                        if (this.previewIndex == returnProphecy2().size() - 1) {
                            this.previewIndex = 0;
                        } else {
                            this.previewIndex++;
                        }
                    } else {
                        this.rotationTimer -= Gdx.graphics.getDeltaTime();
                    }
                }
                break;
        }
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void triggerWhenDrawn() {
        if (chosenBranch()==1){
            if (rally()>7){
                addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
            }
        }
    }

    public int rally() {
        int rally = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion && !(o instanceof AmbushMinion) && !(o instanceof ErikaOrb)) {
                rally++;
            }
        }
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()) {
            case 0:
                if (this.upgraded) {
                    addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
                } else {
                    addToBot(new SFXAction(ID.replace("shadowverse:", "")));
                }
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
                addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -2), -2));
                AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber));
                AbstractDungeon.player.gainGold(this.magicNumber);
                if (this.upgraded) {
                    int r1 = AbstractDungeon.cardRandomRng.random(3);
                    int r2 = AbstractDungeon.cardRandomRng.random(2);
                    AbstractCard c1 = returnProphecy().get(r1);
                    AbstractCard c2 = returnProphecy().get((r1 + r2 + 1) % 4);
                    addToBot(new MakeTempCardInHandAction(c1));
                    addToBot(new MakeTempCardInHandAction(c2));
                }
                break;
            case 1:
                addToBot(new SFXAction("Octrice2"));
                AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber));
                AbstractDungeon.player.gainGold(this.magicNumber);
                addToBot(new MakeTempCardInHandAction(new LightOfHollow()));
                List<AbstractPower> powers = new ArrayList<>();
                for (AbstractPower pow : m.powers) {
                    if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower" && !(pow instanceof BeatOfDeathPower)) {
                        powers.add(pow);
                    }
                }
                if (powers.size()>0){
                    Collections.shuffle(powers);
                    AbstractPower po = powers.get(0);
                    if (po instanceof StrengthPower){
                        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p,po.amount),po.amount));
                    }else if (po instanceof DexterityPower){
                        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p,po.amount),po.amount));
                    }else if (po instanceof RitualPower){
                        addToBot(new ApplyPowerAction(p, p, new RitualPower(p,po.amount,true),po.amount));
                    }else if (po instanceof PlatedArmorPower){
                        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p,po.amount),po.amount));
                    }else if (po instanceof MetallicizePower){
                        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p,po.amount),po.amount));
                    }else if (po instanceof RegenerateMonsterPower){
                        addToBot(new ApplyPowerAction(p, p, new RegenPower(p,po.amount),po.amount));
                    }else if (po instanceof CuriosityPower){
                        addToBot(new ApplyPowerAction(p, p, new CuriosityPower(p,po.amount),po.amount));
                    }else if (po instanceof TimeWarpPower){
                        addToBot(new ApplyPowerAction(p, p, new TimeWarpPower(p)));
                    }else if (po instanceof AngerPower){
                        addToBot(new ApplyPowerAction(p, p, new AngerPower(p,po.amount),po.amount));
                    }else if (po instanceof FlightPower){
                        addToBot(new ApplyPowerAction(p, p, new FlightPower(p,po.amount),po.amount));
                    }else if (po instanceof MalleablePower){
                        addToBot(new ApplyPowerAction(p, p, new MalleablePower(p,po.amount),po.amount));
                    }else if (po instanceof BarricadePower){
                        addToBot(new ApplyPowerAction(p, p, new BarricadePower(p)));
                    }else if (po instanceof BufferPower){
                        addToBot(new ApplyPowerAction(p, p, new BufferPower(p,po.amount),po.amount));
                    }else if (po instanceof EnemyFlameBarrierPower){
                        addToBot(new ApplyPowerAction(p, p, new FlameBarrierPower(p,po.amount),po.amount));
                    }else if (po instanceof ThornsPower){
                        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p,po.amount),po.amount));
                    }else if (po instanceof ArtifactPower){
                        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p,po.amount),po.amount));
                    }else if (po instanceof GenericStrengthUpPower){
                        addToBot(new ApplyPowerAction(p, p, new GenericStrengthUpPower(p,"",po.amount),po.amount));
                    }else if (po instanceof CurseOfGeass){
                        addToBot(new ApplyPowerAction(p, p, new CurseOfGeass(p)));
                    }else if (po instanceof BetterFlightPower){
                        addToBot(new ApplyPowerAction(p, p, new BetterFlightPower(p,po.amount),po.amount));
                    }else if (po instanceof IntangiblePower){
                        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p,po.amount),po.amount));
                    } else {
                        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p,2),2));
                    }
                }
                int r1 = AbstractDungeon.cardRandomRng.random(3);
                AbstractCard c1 = returnProphecy().get(r1);
                addToBot(new MakeTempCardInHandAction(c1));
                break;
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Octrice();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Octrice.this.timesUpgraded;
                Octrice.this.upgraded = true;
                Octrice.this.name = NAME + "+";
                Octrice.this.initializeTitle();
                Octrice.this.baseMagicNumber = 15;
                Octrice.this.magicNumber = Octrice.this.baseMagicNumber;
                Octrice.this.upgradedMagicNumber = true;
                Octrice.this.textureImg = IMG_PATH_EV;
                Octrice.this.loadCardImage(IMG_PATH_EV);
                Octrice.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                Octrice.this.initializeDescription();
                Octrice.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Octrice.this.timesUpgraded;
                Octrice.this.upgraded = true;
                Octrice.this.textureImg = IMG_PATH2;
                Octrice.this.loadCardImage(IMG_PATH2);
                Octrice.this.name = cardStrings2.NAME;
                Octrice.this.initializeTitle();
                Octrice.this.rawDescription = cardStrings2.DESCRIPTION;
                Octrice.this.initializeDescription();
                Octrice.this.previewBranch = 1;
            }
        });
        return list;
    }
}
