package shadowverse.cards.Witch.Spellboost1;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.SpellBoostAction;
import shadowverse.cards.Neutral.Temp.PerjuryOfTruth;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;
import java.util.List;


public class OmenOfTruth
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfTruth";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfTruth");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfTruth2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfTruth.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfTruth2.png";

    public OmenOfTruth() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void triggerWhenDrawn() {
        if (chosenBranch()==1)
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new PerjuryOfTruth()));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
                addToBot((AbstractGameAction) new SpellBoostAction(abstractPlayer, (AbstractCard) this, AbstractDungeon.player.drawPile.group));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new StrengthPower((AbstractCreature) abstractPlayer, 1), 1));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new DexterityPower((AbstractCreature) abstractPlayer, 1), 1));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new Shiv(), 1));
                addToBot((AbstractGameAction) new SFXAction("OmenOfTruth"));
                break;
            case 1:
                AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
                addToBot((AbstractGameAction) new SFXAction("OmenOfTruth2"));
                for (AbstractCard c:abstractPlayer.drawPile.group){
                    if (c.cost>0){
                        c.freeToPlayOnce = true;
                    }
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfTruth();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfTruth.this.timesUpgraded;
                OmenOfTruth.this.upgraded = true;
                OmenOfTruth.this.name = NAME + "+";
                OmenOfTruth.this.initializeTitle();
                OmenOfTruth.this.isInnate = true;
                OmenOfTruth.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                OmenOfTruth.this. initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfTruth.this.timesUpgraded;
                OmenOfTruth.this.upgraded = true;
                OmenOfTruth.this.textureImg = IMG_PATH2;
                OmenOfTruth.this.loadCardImage(IMG_PATH2);
                OmenOfTruth.this.name = cardStrings2.NAME;
                OmenOfTruth.this.initializeTitle();
                OmenOfTruth.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfTruth.this.initializeDescription();
                OmenOfTruth.this.cardsToPreview = new PerjuryOfTruth();
            }
        });
        return list;
    }
}
