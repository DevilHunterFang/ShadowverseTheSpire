package shadowverse.cards.Necromancer.Ghosts;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import shadowverse.cards.Neutral.Temp.Pudding;
import shadowverse.characters.Necromancer;

public class Miyako extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Miyako");
    public static final String ID = "shadowverse:Miyako";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Miyako.png";
    public Miyako(int upgrades) {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
        this.cardsToPreview = (AbstractCard)new Pudding();
    }

    @Override
    public void upgrade() {
        if (this.magicNumber>0){
            upgradeMagicNumber(-1);
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        if (this.timesUpgraded<3){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public void atTurnStart(){
        if (this.baseMagicNumber>0){
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 4));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new IntangiblePlayerPower(p,1)));
        if (this.magicNumber>0){
            addToBot((AbstractGameAction)new SFXAction("Miyako"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Miyako_UB"));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }

    public AbstractCard makeCopy() {
        return new Miyako(this.timesUpgraded);
    }
}
