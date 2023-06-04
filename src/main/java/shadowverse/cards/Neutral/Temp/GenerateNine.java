package shadowverse.cards.Neutral.Temp;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.GenerateNineAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.IzudiaPower;


public class GenerateNine extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:GenerateNine";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GenerateNine");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GenerateNine.png";

    public GenerateNine() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF,4);
        this.exhaust = true;
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GenerateNineAction());
        addToBot(new SFXAction("GenerateNine_EH"));
        addToBot(new ApplyPowerAction(p, p, new IzudiaPower(p, 1), 1));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("GenerateNine"));
        addToBot(new ApplyPowerAction(p, p, new IzudiaPower(p, 1), 1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GenerateNine();
    }
}

