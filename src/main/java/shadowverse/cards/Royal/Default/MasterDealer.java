package shadowverse.cards.Royal.Default;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;

public class MasterDealer extends CustomCard {
    public static final String ID = "shadowverse:MasterDealer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MasterDealer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MasterDealer.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public MasterDealer() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 16;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> true, abstractCards -> {
            AbstractCard card = null;
            for (AbstractCard c : abstractCards) {
                card = c;
            }
            int i = 0;
            for (AbstractCard c : p.hand.group) {
                if (c != card && c != this) {
                    addToBot(new ExhaustSpecificCardAction(c, p.hand));
                    i++;
                }
            }
            addToBot(new DrawCardAction(i));
        }));
        addToBot(new GainBlockAction(p, p, this.block));
    }


    @Override
    public AbstractCard makeCopy() {
        return new MasterDealer();
    }
}
