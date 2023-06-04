package shadowverseCharbosses.cards.bishop;

import shadowverseCharbosses.cards.AbstractBossCard;
import shadowverseCharbosses.powers.cardpowers.EnemyFlameBarrierPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import shadowverse.cards.Neutral.Temp.LionBless;
import shadowverse.characters.Bishop;

public class SacredLion extends AbstractBossCard {
    public static final String ID = "shadowverse:SacredLion";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SacredLion");

    public static final String IMG_PATH = "img/cards/SacredLion.png";

    public SacredLion() {
        super(ID, cardStrings.NAME, IMG_PATH, 2, cardStrings.DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 30;
        this.cardsToPreview = (AbstractCard)new LionBless();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new BufferPower((AbstractCreature)m, 1), 1));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyFlameBarrierPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(6);
            upgradeMagicNumber(2);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new SacredLion();
    }
}
