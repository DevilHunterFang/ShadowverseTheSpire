package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cardmods.UnseenStrengthMod;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


public class UnseenStrength
        extends CustomCard {
    public static final String ID = "shadowverse:UnseenStrength";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnseenStrength");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UnseenStrength.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public UnseenStrength() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.selfRetain = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        int count = 0;
        for (AbstractCard c : p.hand.group) {
            if (c instanceof Puppet)
                count++;
        }
        if (count < 1) {
            canUse = false;
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Puppet)
                count++;
        }
        if (count < 1) {
            glow = false;
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("UnseenStrength"));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return card instanceof Puppet;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                CardModifierManager.addModifier(c, new UnseenStrengthMod());
                int count = 0;
                for (AbstractCard ca : abstractPlayer.exhaustPile.group) {
                    if (ca.type == CardType.ATTACK)
                        count++;
                }
                if (count >= 10) {
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, 2), 2));
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 2), 2));
                }
                if (count >= 20) {
                    c.upgrade();
                }
                c.superFlash();
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new UnseenStrength();
    }
}

