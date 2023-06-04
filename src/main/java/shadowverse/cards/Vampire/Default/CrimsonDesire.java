package shadowverse.cards.Vampire.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Vampire;

public class CrimsonDesire extends CustomCard {
    public static final String ID = "shadowverse:CrimsonDesire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CrimsonDesire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CrimsonDesire.png";

    public CrimsonDesire() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p,p,2));
        addToBot(new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint()));
        addToBot(new MakeTempCardInHandAction((AbstractCard)new Miracle()));
    }



    public AbstractCard makeCopy() {
        return new CrimsonDesire();
    }
}
