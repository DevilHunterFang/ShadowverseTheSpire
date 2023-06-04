package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.List;


public class Ginsetsu
        extends CustomCard implements BranchableUpgradeCard {
    public static final String ID = "shadowverse:Ginsetsu";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ginsetsu");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ginsetsu2");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ginsetsu.png";
    public static final String IMG_PATH2 = "img/cards/Ginsetsu2.png";

    public Ginsetsu() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.misc = 9;
        this.baseDamage = this.misc;
        this.baseBlock = 18;
        this.cardsToPreview = (AbstractCard) new OneTailFox();
    }


    public void upgrade() {
        ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void applyPowers() {
        this.baseDamage = this.misc;
        if (this.upgraded && chosenBranch()==0){
            this.baseBlock = 27;
        }else {
            this.baseBlock = 18;
        }
        super.applyPowers();
        initializeDescription();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        switch (chosenBranch()){
            case 0:
                addToBot((AbstractGameAction) new SFXAction("Ginsetsu"));
                addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 4));
                break;
            case 1:
                addToBot((AbstractGameAction) new SFXAction("Ginsetsu2"));
                addToBot((AbstractGameAction) new NecromanceAction(9,new MakeTempCardInHandAction(c,1),
                        new MakeTempCardInHandAction(c,4)));
                break;
        }
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Ginsetsu();
    }

    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ginsetsu.this.timesUpgraded;
                Ginsetsu.this.upgraded = true;
                Ginsetsu.this.name = NAME + "+";
                Ginsetsu.this.initializeTitle();
                Ginsetsu.this.baseBlock = 27;
                Ginsetsu.this.upgradedBlock = true;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Ginsetsu.this.timesUpgraded;
                Ginsetsu.this.upgraded = true;
                Ginsetsu.this.textureImg = IMG_PATH2;
                Ginsetsu.this.loadCardImage(IMG_PATH2);
                Ginsetsu.this.name = cardStrings2.NAME;
                Ginsetsu.this.initializeTitle();
                Ginsetsu.this.rawDescription = cardStrings2.DESCRIPTION;
                Ginsetsu.this.initializeDescription();
                Ginsetsu.this.upgradeBaseCost(2);
            }
        });
        return list;
    }
}

