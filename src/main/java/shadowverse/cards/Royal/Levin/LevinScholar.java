package shadowverse.cards.Royal.Levin;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.LevinScholarAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class LevinScholar extends CustomCard {
    public static final String ID = "shadowverse:LevinScholar";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LevinScholar.png";

    public LevinScholar() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 15;
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean co = false;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c instanceof Albert) {
                co = true;
                break;
            }
        }
        if (co) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Co"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        addToBot(new LevinScholarAction());
    }


    @Override
    public AbstractCard makeCopy() {
        return new LevinScholar();
    }
}



