package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class SpiritCurator extends CustomCard {
    public static final String ID = "shadowverse:SpiritCurator";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SpiritCurator");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SpiritCurator.png";

    public SpiritCurator() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SpiritCurator"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot(new BurialAction(1, new DrawCardAction(1)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID))
                addToBot(new DrawCardAction(1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SpiritCurator();
    }
}
