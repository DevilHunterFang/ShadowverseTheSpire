package shadowverse.powers;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.cards.Neutral.Status.GiantGhost;
import shadowverse.cards.Neutral.Temp.MasqueradeGhost_Copy;

public class MasqueradeGhostPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:MasqueradeGhostPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MasqueradeGhostPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean isLastWord;

    public MasqueradeGhostPower(AbstractCreature owner, int amount, boolean isLastWord) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isLastWord = isLastWord;
        updateDescription();
        this.img = new Texture("img/powers/MasqueradeGhostPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
        updateExistingGhost();
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + (isLastWord ? DESCRIPTIONS[2] : DESCRIPTIONS[1]);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card instanceof Ghost) {
            addToBot(new SFXAction("MasqueradeGhostPower"));
            addToBot(new MakeTempCardInHandAction(new GiantGhost()));
        }
    }

    @Override
    public void atStartOfTurn() {
        if (isLastWord) {
            addToBot(new MakeTempCardInHandAction(new MasqueradeGhost_Copy()));
        }
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    private void updateExistingGhost() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Ghost) {
                if (!c.upgraded) {
                    c.baseDamage = 4 + this.amount;
                    continue;
                }
                c.baseDamage = 6 + this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof Ghost) {
                if (!c.upgraded) {
                    c.baseDamage = 4 + this.amount;
                    continue;
                }
                c.baseDamage = 6 + this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof Ghost) {
                if (!c.upgraded) {
                    c.baseDamage = 4 + this.amount;
                    continue;
                }
                c.baseDamage = 6 + this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof Ghost) {
                if (!c.upgraded) {
                    c.baseDamage = 4 + this.amount;
                    continue;
                }
                c.baseDamage = 6 + this.amount;
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Ghost) {
                if (!c.upgraded) {
                    c.baseDamage = 4 + this.amount;
                    continue;
                }
                c.baseDamage = 6 + this.amount;
            }
        }
    }
}


