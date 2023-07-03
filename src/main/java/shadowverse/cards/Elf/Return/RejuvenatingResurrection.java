package shadowverse.cards.Elf.Return;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;


public class RejuvenatingResurrection extends CustomCard {
    public static final String ID = "shadowverse:RejuvenatingResurrection";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RejuvenatingResurrection");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RejuvenatingResurrection.png";

    public RejuvenatingResurrection() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        boolean hasAttack = false;
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK || c.type == CardType.POWER)
                hasAttack = true;
        }
        if (!hasAttack) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            canUse = false;
        }
        return canUse;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("RejuvenatingResurrection"));
        if (this.upgraded) {
            addToBot(new MoveCardsAction(abstractPlayer.hand, abstractPlayer.discardPile, card -> card.type == CardType.ATTACK || card.type==CardType.POWER, 1, abstractCards -> {
                for (AbstractCard c :
                        abstractCards) {
                    if (c.canUpgrade())
                        c.upgrade();
                }
            }));
        } else {
            addToBot(new MoveCardsAction(abstractPlayer.hand, abstractPlayer.discardPile, card -> card.type == CardType.ATTACK, 1, abstractCards -> {
                for (AbstractCard c :
                        abstractCards) {
                    if (c.canUpgrade())
                        c.upgrade();
                }
            }));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RejuvenatingResurrection();
    }
}
