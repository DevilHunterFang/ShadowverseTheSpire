package shadowverse.cards.Vampire.Wrath;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.WrathPower;


public class AntelopeWarrior
        extends CustomCard {
    public static final String ID = "shadowverse:AntelopeWarrior";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AntelopeWarrior");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AntelopeWarrior.png";

    public AntelopeWarrior() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(WrathPower.POWER_ID)&&!p.hasPower(EpitaphPower.POWER_ID)){
            addToBot((AbstractGameAction) new LoseHPAction((AbstractCreature) p, (AbstractCreature) p, 1));
        }else {
            addToBot((AbstractGameAction)new HealAction(p,p,3));
        }
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AntelopeWarrior();
    }
}

