package shadowverse.cards.Royal.Default;



import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionSummonAction;
import shadowverse.characters.Royal;
import shadowverse.orbs.FrontguardGeneral;
import shadowverse.orbs.Minion;


public class HonoredFrontguardGeneral extends CustomCard {
    public static final String ID = "shadowverse:HonoredFrontguardGeneral";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HonoredFrontguardGeneral.png";

    public HonoredFrontguardGeneral(int upgrades) {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.exhaust = true;
    }

    public HonoredFrontguardGeneral(){
        this(0);
    }

    @Override
    public void upgrade() {
        this.timesUpgraded++;
        upgradeMagicNumber(1);
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("HonoredFrontguardGeneral"));
        Minion o = new FrontguardGeneral();
        o.buff(this.magicNumber,this.magicNumber);
        addToBot((AbstractGameAction)new MinionSummonAction(o));
    }


    @Override
    public AbstractCard makeCopy() {
        return new HonoredFrontguardGeneral();
    }
}

