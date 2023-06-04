package shadowverse.cards.Vampire.Bat;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.Neutral.Temp.ForestBat;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.VeightPower;
import shadowverse.powers.WrathPower;
import shadowverse.stance.Vengeance;

import java.util.ArrayList;
import java.util.List;


public class Veight
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Veight";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Veight");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Veight2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Veight.png";
    public static final String IMG_PATH2 = "img/cards/Veight2.png";

    public Veight() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 7;
        this.cardsToPreview = (AbstractCard) new ForestBat();
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Veight"));
                addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new VeightPower((AbstractCreature) p, this.magicNumber), this.magicNumber));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Veight2"));
                addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) p, (AbstractCreature) p, this.block));
                addToBot(new LoseHPAction(p,p,1));
                addToBot(new DrawCardAction(1));
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                addToBot(new LoseHPAction(p,p,1));
                addToBot(new DrawCardAction(1));
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                if (p.hasPower(EpitaphPower.POWER_ID) || p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(WrathPower.POWER_ID)) {
                    addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1)));
                    addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1)));
                }
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Veight();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Veight.this.timesUpgraded;
                Veight.this.upgraded = true;
                Veight.this.name = NAME + "+";
                Veight.this.initializeTitle();
                Veight.this.baseBlock = 11;
                Veight.this.upgradedBlock = true;
                Veight.this.initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Veight.this.timesUpgraded;
                Veight.this.upgraded = true;
                Veight.this.textureImg = IMG_PATH2;
                Veight.this.loadCardImage(IMG_PATH2);
                Veight.this.name = cardStrings2.NAME;
                Veight.this.baseBlock = 6;
                Veight.this.upgradedDamage = true;
                Veight.this.initializeTitle();
                Veight.this.rawDescription = cardStrings2.DESCRIPTION;
                Veight.this.initializeDescription();
                Veight.this.rarity = CardRarity.UNCOMMON;
                Veight.this.setDisplayRarity(Veight.this.rarity);
            }
        });
        return list;
    }
}

