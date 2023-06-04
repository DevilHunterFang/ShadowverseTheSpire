package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.BlockPerCardAction;
import shadowverse.action.SatanaelAction;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.List;

public class Satan extends AbstractNeutralCard implements BranchableUpgradeCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Satan");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Satanael");
    public static final String ID = "shadowverse:Satan";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Satan.png";
    public static final String IMG_PATH2 = "img/cards/Satanael.png";

    public static ArrayList<AbstractCard> returnCocytusDeck() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Flamelord());
        list.add(new Desire());
        list.add(new Scorpion());
        list.add(new HellBeast());
        list.add(new WrathfulIcefiend());
        list.add(new ViciousCommander());
        list.add(new DemonOfPurgatory());
        list.add(new Behemoth());
        list.add(new InfernalGaze());
        list.add(new InfernalSurge());
        list.add(new HeavenFall());
        list.add(new EarthFall());
        list.add(new Astaroth());
        return list;
    }



    public static AbstractCard returnCocytusCard(Random rng) {
        return returnCocytusDeck().get(rng.random(returnCocytusDeck().size() - 1));
    }

    public Satan() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
        this.exhaust = true;
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void update() {
        if (chosenBranch()==0){
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                    Shadowverse.Accelerate(this)) {
                setCostForTurn(1);
                this.type = CardType.SKILL;
            } else {
                if (this.type == CardType.SKILL) {
                    if (this.upgraded){
                        setCostForTurn(3);
                    }else {
                        setCostForTurn(4);
                    }
                    this.type = CardType.POWER;
                }
            }
        }
        super.update();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        switch (chosenBranch()){
            case 0:
                if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
                    addToBot((AbstractGameAction) new SFXAction("Satan_Accelerate"));
                    ArrayList<String> l = new ArrayList<String>();
                    while (true) {
                        AbstractCard c = returnCocytusCard(AbstractDungeon.cardRandomRng).makeCopy();
                        if (!l.contains(c.cardID)) {
                            l.add(c.cardID);
                            addToBot((AbstractGameAction) new MakeTempCardInDrawPileAction(c, 1, true, true));
                        }
                        if (l.size() >= 4) {
                            break;
                        }
                    }
                } else {
                    addToBot((AbstractGameAction) new SFXAction("Satan"));
                    for (AbstractCard c : abstractPlayer.drawPile.group) {
                        addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c, abstractPlayer.drawPile));
                    }
                    ArrayList<AbstractCard> cocytusDeck = returnCocytusDeck();
                    for (AbstractCard ca : cocytusDeck) {
                        addToBot((AbstractGameAction) new MakeTempCardInDrawPileAction(ca, 1, true, true));
                    }
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
                ++Satan.this.timesUpgraded;
                Satan.this.upgraded = true;
                Satan.this.name = NAME + "+";
                Satan.this.initializeTitle();
                Satan.this.upgradeBaseCost(3);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Satan.this.timesUpgraded;
                Satan.this.upgraded = true;
                Satan.this.textureImg = IMG_PATH2;
                Satan.this.loadCardImage(IMG_PATH2);
                Satan.this.name = cardStrings2.NAME;
                Satan.this.initializeTitle();
                Satan.this.rawDescription = cardStrings2.DESCRIPTION;
                Satan.this.baseBlock = 3;
                Satan.this.upgradedBlock = true;
                Satan.this.upgradeBaseCost(2);
                Satan.this.initializeDescription();
            }
        });
        return list;
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Satan();
    }
}

