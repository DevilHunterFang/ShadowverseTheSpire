package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class EternalVow
        extends CustomCard {
    public static final String ID = "shadowverse:EternalVow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EternalVow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EternalVow.png";

    public EternalVow() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("EternalVow"));
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c.color == Necromancer.Enums.COLOR_PURPLE) {
                if (c.type == CardType.SKILL && c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)) {
                    continue;
                }
                if (!this.upgraded) {
                    addToBot((AbstractGameAction) new ReduceCostForTurnAction(c, 1));
                } else {
                    addToBot((AbstractGameAction) new ReduceCostAction(c));
                }
                c.flash();
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new EternalVow();
    }
}


