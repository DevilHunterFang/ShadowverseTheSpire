package shadowverse.cards.Necromancer.Burial;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReanimateAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Necromancer;
import shadowverse.powers.CarnivalNecromancerPower;


public class CarnivalNecromancer extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:CarnivalNecromancer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CarnivalNecromancer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CarnivalNecromancer.png";

    public CarnivalNecromancer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF,3);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
        
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("CarnivalNecromancer_EH"));
        addToBot(new ApplyPowerAction(p, p, new CarnivalNecromancerPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ReanimateAction(4));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("CarnivalNecromancer"));
        addToBot(new ApplyPowerAction(p, p, new CarnivalNecromancerPower(p, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new CarnivalNecromancer();
    }
}

