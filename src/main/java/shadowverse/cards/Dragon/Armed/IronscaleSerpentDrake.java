package shadowverse.cards.Dragon.Armed;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class IronscaleSerpentDrake extends CustomCard {
    public static final String ID = "shadowverse:IronscaleSerpentDrake";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:IronscaleSerpentDrake");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/IronscaleSerpentDrake.png";

    public IronscaleSerpentDrake() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 8;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        if (abstractPlayer.hasPower(StrengthPower.POWER_ID) && abstractPlayer.getPower(StrengthPower.POWER_ID).amount > 0) {
            addToBot(new GainBlockAction(abstractPlayer, this.block));
        }
        if (this.hasTag(AbstractShadowversePlayer.Enums.ARMED)) {
            addToBot(new GainBlockAction(abstractPlayer, this.block));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new IronscaleSerpentDrake();
    }
}

