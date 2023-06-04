package shadowverse.cards.Elf.Return;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;


public class Slade extends CustomCard {
    public static final String ID = "shadowverse:Slade";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Slade");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Slade.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ChooseToReduceCost")).TEXT;

    public Slade() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 4;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Slade"));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, true, card -> {
            return card.color == Elf.Enums.COLOR_GREEN && card.type == CardType.ATTACK;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                c.flash();
                addToBot((AbstractGameAction) new ReduceCostForTurnAction(c, 1));
            }
        }));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Slade();
    }
}
