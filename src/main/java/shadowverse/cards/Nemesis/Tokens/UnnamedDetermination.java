package shadowverse.cards.Nemesis.Tokens;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Nemesis;

public class UnnamedDetermination extends CustomCard {
    public static final String ID = "shadowverse:UnnamedDetermination";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnnamedDetermination");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UnnamedDetermination.png";

    public UnnamedDetermination() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new SFXAction("UnnamedDetermination "));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint()));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle()));
        int count = 0;
        for (AbstractCard c:p.exhaustPile.group){
            if (c.type==CardType.ATTACK)
                count++;
        }
        if (count>=10)
            addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
    }



    public AbstractCard makeCopy() {
        return new UnnamedDetermination();
    }
}
