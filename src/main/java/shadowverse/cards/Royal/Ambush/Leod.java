package shadowverse.cards.Royal.Ambush;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.MinionSummonAction;
import shadowverse.cards.Neutral.Temp.VeiledReckoning;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;

import java.util.ArrayList;
import java.util.List;

public class Leod
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Leod";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Leod");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Leod2");
    public static final String NAME = cardStrings.NAME;
    public static final String NAME2 = cardStrings2.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION2 = cardStrings2.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Leod.png";
    public static final String IMG_PATH_EV = "img/cards/Leod_Ev.png";
    public static final String IMG_PATH2 = "img/cards/Leod2.png";

    public Leod() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new VeiledReckoning();
    }


    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction)new SFXAction("Leod"));
                AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new AmbushMinion(1,this.magicNumber,this.makeStatEquivalentCopy(),false)));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Leod2"));
                AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new AmbushMinion(1,this.magicNumber,this.makeStatEquivalentCopy(),false)));
                break;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Leod();
    }


    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Leod.this.timesUpgraded;
                Leod.this.upgraded = true;
                Leod.this.name = NAME + "+";
                Leod.this.initializeTitle();
                Leod.this.isInnate = true;
                Leod.this.baseMagicNumber = 3;
                Leod.this.magicNumber = Leod.this.baseMagicNumber;
                Leod.this.upgradedMagicNumber = true;
                Leod.this.textureImg = IMG_PATH_EV;
                Leod.this.loadCardImage(IMG_PATH_EV);
                Leod.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                initializeDescription();
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Leod.this.timesUpgraded;
                Leod.this.upgraded = true;
                Leod.this.baseMagicNumber = 4;
                Leod.this.magicNumber = Leod.this.baseMagicNumber;
                Leod.this.upgradedMagicNumber = true;
                Leod.this.textureImg = IMG_PATH2;
                Leod.this.loadCardImage(IMG_PATH2);
                Leod.this.name = cardStrings2.NAME;
                Leod.this.initializeTitle();
                Leod.this.cardsToPreview = null;
                Leod.this.rawDescription = cardStrings2.DESCRIPTION;
                Leod.this.initializeDescription();
                Leod.this.rarity = CardRarity.RARE;
                Leod.this.setDisplayRarity(Leod.this.rarity);
            }
        });
        return list;
    }
}


