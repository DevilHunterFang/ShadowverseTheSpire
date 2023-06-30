package shadowverse.cards.Dragon.Tempo;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.HellFlameDragon;
import shadowverse.characters.Dragon;
import shadowverse.powers.DragonsongFlutePower;
import shadowverse.powers.OverflowPower;

public class DragonsongFlute
        extends CustomCard {
    public static final String ID = "shadowverse:DragonsongFlute";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DragonsongFlute");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DragonsongFlute.png";

    public DragonsongFlute() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new HellFlameDragon();
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(OverflowPower.POWER_ID)){
            TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
            if (p.amount2 > 0){
                for (AbstractCard c : abstractPlayer.hand.group){
                    if (c.type != AbstractCard.CardType.SKILL && c.type != AbstractCard.CardType.POWER && c.cost < 2){
                        addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
                        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
                    }
                }
            }
        }
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DragonsongFlutePower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DragonsongFlute();
    }
}

