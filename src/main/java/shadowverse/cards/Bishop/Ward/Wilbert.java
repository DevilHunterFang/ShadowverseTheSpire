package shadowverse.cards.Bishop.Ward;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.HolyCavalier;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.NextTurnHC;
import shadowverse.powers.Wilbert2Power;
import shadowverse.powers.WilbertPower;

import java.util.ArrayList;
import java.util.List;

public class Wilbert
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Wilbert";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Wilbert");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Wilbert2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Wilbert.png";
    public static final String IMG_PATH2 = "img/cards/Wilbert2.png";

    public Wilbert() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 15;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new HolyCavalier();
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.exhaust = true;
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction)new SFXAction("Wilbert"));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLDENROD, true)));
                addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
                if (!p.hasPower(WilbertPower.POWER_ID)){
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new WilbertPower((AbstractCreature) p)));
                }
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new NextTurnHC(p,this.magicNumber)));
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Wilbert2"));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLDENROD, true)));
                addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
                if (!p.hasPower(Wilbert2Power.POWER_ID)){
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Wilbert2Power((AbstractCreature) p)));
                }
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Wilbert();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Wilbert.this.timesUpgraded;
                Wilbert.this.upgraded = true;
                Wilbert.this.name = NAME + "+";
                Wilbert.this.baseBlock = 18;
                Wilbert.this.baseMagicNumber = 2;
                Wilbert.this.magicNumber = Wilbert.this.baseMagicNumber;
                Wilbert.this.upgradedBlock= true;
                Wilbert.this.upgradedMagicNumber = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Wilbert.this.timesUpgraded;
                Wilbert.this.upgraded = true;
                Wilbert.this.textureImg = IMG_PATH2;
                Wilbert.this.loadCardImage(IMG_PATH2);
                Wilbert.this.name = cardStrings2.NAME;
                Wilbert.this.initializeTitle();
                Wilbert.this.rawDescription = cardStrings2.DESCRIPTION;
                Wilbert.this.initializeDescription();
                Wilbert.this.baseBlock = 8;
                Wilbert.this.upgradedBlock = true;
                Wilbert.this.upgradeBaseCost(1);
                Wilbert.this.tags.remove(AbstractShadowversePlayer.Enums.LASTWORD);
            }
        });
        return list;
    }
}


