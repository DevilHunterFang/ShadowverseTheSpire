package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import shadowverse.cards.Royal.Hero.MorgensternMaid;
import shadowverse.characters.Royal;

public class MorgensternMaid1 extends CustomCard {
    public static final String ID = "shadowverse:MorgensternMaid1";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MorgensternMaid.png";

    public MorgensternMaid1() {
        this(false);
    }

    public MorgensternMaid1(boolean upgraded) {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 10;
        if (upgraded) {
            this.upgrade();
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        this.applyPowers();
        addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
        addToBot((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new VulnerablePower(p, 1, false)));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public AbstractCard makeCopy() {
        return new MorgensternMaid();
    }
}
