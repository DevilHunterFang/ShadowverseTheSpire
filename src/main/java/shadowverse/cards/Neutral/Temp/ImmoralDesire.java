package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.ImmoralDesireMod1;
import shadowverse.cardmods.ImmoralDesireMod2;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;


public class ImmoralDesire
        extends CustomCard {
    public static final String ID = "shadowverse:ImmoralDesire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImmoralDesire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ImmoralDesire.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public ImmoralDesire() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        int count = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.ATTACK)
                count++;
        }
        if (count < 2) {
            canUse = false;
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK)
                count++;
        }
        if (count < 2) {
            glow = false;
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("ImmoralDesire"));
        ArrayList<AbstractCard> chose2 = new ArrayList<>();
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return card.type == CardType.ATTACK;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                chose2.add(c);
                CardModifierManager.addModifier(c, (AbstractCardModifier) new ImmoralDesireMod1());
                c.superFlash();
            }
        }));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return card.type == CardType.ATTACK && !chose2.contains(card);
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                CardModifierManager.addModifier(c, (AbstractCardModifier) new ImmoralDesireMod2());
                c.superFlash();
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ImmoralDesire();
    }
}

