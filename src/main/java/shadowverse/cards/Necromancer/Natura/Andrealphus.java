package shadowverse.cards.Necromancer.Natura;

import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.AndrealphusMod;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;

public class Andrealphus extends CustomCard {
    public static final String ID = "shadowverse:Andrealphus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Andrealphus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Andrealphus.png";

    public Andrealphus() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        addToBot(new AbstractGameAction() {
            public void update() {
                this.isDone = true;
                for (AbstractCard c : p.hand.group) {
                    if (c.type==CardType.ATTACK) {
                        CardModifierManager.addModifier(c, (AbstractCardModifier)new AndrealphusMod());
                        c.superFlash();
                    }
                }
            }
        });
    }

    public AbstractCard makeCopy() {
        return new Andrealphus();
    }
}
