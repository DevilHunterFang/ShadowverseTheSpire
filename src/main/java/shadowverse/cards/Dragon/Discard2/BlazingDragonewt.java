package shadowverse.cards.Dragon.Discard2;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.characters.Dragon;


public class BlazingDragonewt
        extends CustomCard {
    public static final String ID = "shadowverse:BlazingDragonewt";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlazingDragonewt");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BlazingDragonewt.png";
    private boolean trigger;

    public BlazingDragonewt() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("BlazingDragonewt"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        if (!trigger) {
            trigger = true;
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1), 1));
        }
        addToBot(new DiscardAction(abstractPlayer, abstractPlayer, 1, false));
        addToBot(new DrawCardAction(1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BlazingDragonewt();
    }
}

