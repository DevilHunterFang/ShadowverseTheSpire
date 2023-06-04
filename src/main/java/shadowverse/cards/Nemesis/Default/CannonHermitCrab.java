package shadowverse.cards.Nemesis.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.orbs.CannonHermitCrabOrb;


public class CannonHermitCrab extends CustomCard {
    public static final String ID = "shadowverse:CannonHermitCrab";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CannonHermitCrab.png";

    public CannonHermitCrab() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.maxOrbs<5){
            int toIncrease = 5-abstractPlayer.maxOrbs;
            addToBot((AbstractGameAction)new IncreaseMaxOrbAction(toIncrease));
        }
        addToBot((AbstractGameAction) new MinionSummonAction(new CannonHermitCrabOrb()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new CannonHermitCrab();
    }
}

