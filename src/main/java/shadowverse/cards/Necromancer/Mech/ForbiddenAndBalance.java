package shadowverse.cards.Necromancer.Mech;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class ForbiddenAndBalance extends CustomCard {
    public static final String ID = "shadowverse:ForbiddenAndBalance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ForbiddenAndBalance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ForbiddenAndBalance.png";

    public ForbiddenAndBalance() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
            flash();
            addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
            addToBot((AbstractGameAction)new SFXAction("spell_boost"));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("ForbiddenAndBalance"));
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
        addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)p, (AbstractCreature)p, 1, false));
        this.cost = 2;
    }

    public AbstractCard makeCopy() {
        return new ForbiddenAndBalance();
    }
}
