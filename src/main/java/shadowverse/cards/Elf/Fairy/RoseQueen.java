package shadowverse.cards.Elf.Fairy;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.Fairy;
import shadowverse.cards.Neutral.Temp.ThronBurst;
import shadowverse.characters.Elf;


public class RoseQueen
        extends CustomCard {
    public static final String ID = "shadowverse:RoseQueen";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RoseQueen");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RoseQueen.png";

    public RoseQueen() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 25;
        this.cardsToPreview = new ThronBurst();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("RoseQueen"));
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c instanceof Fairy) {
                addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
                addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RoseQueen();
    }
}

