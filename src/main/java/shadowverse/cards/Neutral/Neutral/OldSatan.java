package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.BlockPerCardAction;
import shadowverse.action.SatanaelAction;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Temp.*;

import java.util.ArrayList;
import java.util.List;

public class OldSatan extends AbstractNeutralCard implements BranchableUpgradeCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OldSatan");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Satanael");
    public static final String ID = "shadowverse:OldSatan";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OldSatan.png";
    public static final String IMG_PATH2 = "img/cards/Satanael.png";

    public static ArrayList<AbstractCard> returnApocalypseDeck() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Servant());
        list.add(new Servant());
        list.add(new Servant());
        list.add(new SilentRider());
        list.add(new SilentRider());
        list.add(new SilentRider());
        list.add(new Dis());
        list.add(new Dis());
        list.add(new Dis());
        list.add(new Astaroth());
        return list;
    }

    public OldSatan() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("OldSatan"));
                for (AbstractCard c : abstractPlayer.drawPile.group) {
                    addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c, abstractPlayer.drawPile));
                }
                ArrayList<AbstractCard> apocalypseDeck = returnApocalypseDeck();
                for (AbstractCard ca : apocalypseDeck) {
                    addToBot((AbstractGameAction) new MakeTempCardInDrawPileAction(ca, 1, true, true));
                }
                break;
            case 1:
                addToBot((AbstractGameAction)new SFXAction("Satanael"));
                addToBot((AbstractGameAction)new BlockPerCardAction(this.block));
                addToBot((AbstractGameAction)new SatanaelAction());
                break;
        }

    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OldSatan.this.timesUpgraded;
                OldSatan.this.upgraded = true;
                OldSatan.this.name = NAME + "+";
                OldSatan.this.initializeTitle();
                OldSatan.this.upgradeBaseCost(3);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OldSatan.this.timesUpgraded;
                OldSatan.this.upgraded = true;
                OldSatan.this.textureImg = IMG_PATH2;
                OldSatan.this.loadCardImage(IMG_PATH2);
                OldSatan.this.name = cardStrings2.NAME;
                OldSatan.this.initializeTitle();
                OldSatan.this.rawDescription = cardStrings2.DESCRIPTION;
                OldSatan.this.baseBlock = 3;
                OldSatan.this.upgradedBlock = true;
                OldSatan.this.upgradeBaseCost(2);
                OldSatan.this.initializeDescription();
            }
        });
        return list;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OldSatan();
    }
}

