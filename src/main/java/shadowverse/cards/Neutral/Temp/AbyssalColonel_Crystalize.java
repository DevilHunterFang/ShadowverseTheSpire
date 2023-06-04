package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class AbyssalColonel_Crystalize
        extends CustomCard {
    public static final String ID = "shadowverse:AbyssalColonel_Crystalize";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbyssalColonel_Crystalize");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AbyssalColonel.png";

    public AbyssalColonel_Crystalize() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.cardsToPreview = new AbyssalColonel_Temp();
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void atTurnStart() {
        if (this.baseMagicNumber>0){
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
            if (this.magicNumber == 0){
                addToBot(new ExhaustSpecificCardAction(this,AbstractDungeon.player.hand));
            }
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),1));
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new AbyssalColonel_Crystalize();
    }
}

