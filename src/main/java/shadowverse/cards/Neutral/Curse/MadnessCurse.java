package shadowverse.cards.Neutral.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MadnessCurse extends CustomCard {
    public static final String ID = "shadowverse:MadnessCurse";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MadnessCurse");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MadnessCurse.png";

    public MadnessCurse() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.cardsToPreview = new Madness();
    }

    @Override
    public void upgrade() {
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("MadnessCurse"));
        AbstractCard m = this.cardsToPreview.makeStatEquivalentCopy();
        m.upgrade();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(m,10-abstractPlayer.hand.group.size()));
    }

    @Override
    public AbstractCard makeCopy(){
        return new MadnessCurse();
    }
}
