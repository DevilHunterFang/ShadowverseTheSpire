package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Temp.EvidenceOfOne;
import shadowverse.powers.OmenOfOnePower;
import shadowverse.powers.OmenOfOnePower2;


public class OmenOfOne
        extends AbstractNeutralCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:OmenOfOne";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfOne");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfOne2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfOne.png";
    public static final String IMG_PATH2 = "img/cards/OmenOfOne2.png";

    public OmenOfOne() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                boolean deckCheck = true;
                ArrayList<String> tmp = new ArrayList<>();
                for (AbstractCard c : abstractPlayer.drawPile.group) {
                    if (tmp.contains(c.cardID)) {
                        deckCheck = false;
                        break;
                    }
                    tmp.add(c.cardID);
                }
                if (deckCheck) {
                    AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
                    addToBot(new SFXAction("OmenOfOne"));
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new OmenOfOnePower(abstractPlayer)));
                }
                break;
            case 1:
                AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
                addToBot(new SFXAction("OmenOfOne2"));
                ArrayList<String> tmp2 = new ArrayList<>();
                for (AbstractCard c : abstractPlayer.drawPile.group) {
                    if (tmp2.contains(c.cardID)) {
                        addToBot(new ExhaustSpecificCardAction(c,abstractPlayer.drawPile));
                    }else {
                        tmp2.add(c.cardID);
                    }
                }
                for (AbstractCard card : abstractPlayer.discardPile.group){
                    if (tmp2.contains(card.cardID)) {
                        addToBot(new ExhaustSpecificCardAction(card,abstractPlayer.discardPile));
                    }else {
                        tmp2.add(card.cardID);
                    }
                }
                if(tmp2.size()>=abstractPlayer.masterDeck.size()/2){
                    addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new OmenOfOnePower2(abstractPlayer)));
                }
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                break;
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfOne();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfOne.this.timesUpgraded;
                OmenOfOne.this.upgraded = true;
                OmenOfOne.this.name = NAME + "+";
                OmenOfOne.this.initializeTitle();
                int diff = OmenOfOne.this.costForTurn - OmenOfOne.this.cost;
                OmenOfOne.this.cost = 1;
                if (OmenOfOne.this.costForTurn > 0) {
                    OmenOfOne.this.costForTurn = OmenOfOne.this.cost + diff;
                }

                if (OmenOfOne.this.costForTurn < 0) {
                    OmenOfOne.this.costForTurn = 0;
                }
                OmenOfOne.this.upgradedCost = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++OmenOfOne.this.timesUpgraded;
                OmenOfOne.this.upgraded = true;
                OmenOfOne.this.textureImg = IMG_PATH2;
                OmenOfOne.this.loadCardImage(IMG_PATH2);
                OmenOfOne.this.name = cardStrings2.NAME;
                OmenOfOne.this.initializeTitle();
                OmenOfOne.this.rawDescription = cardStrings2.DESCRIPTION;
                OmenOfOne.this.initializeDescription();
                OmenOfOne.this.cardsToPreview = new EvidenceOfOne();
            }
        });
        return list;
    }
}

