package shadowverse.cards.Necromancer.Shadows;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.action.InvocationAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;
import shadowverse.powers.GremoryPower;
import shadowverse.powers.GremoryUsedPower;


public class Gremory extends CustomCard {
    public static final String ID = "shadowverse:Gremory";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Gremory");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Gremory.png";
    public static boolean dupCheck = true;

    public Gremory() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void atTurnStart() {
        int playerNecromance = 0;
        if (AbstractDungeon.player.hasPower(Cemetery.POWER_ID)) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
        if (playerNecromance >= masterDeckAmt && dupCheck) {
            dupCheck = false;
            if (AbstractDungeon.player.discardPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new DiscardToHandAction((AbstractCard) this));
            } else if (AbstractDungeon.player.drawPile.contains((AbstractCard) this)) {
                addToBot((AbstractGameAction) new ReduceCostForTurnAction((AbstractCard) this, 9));
                addToBot((AbstractGameAction) new InvocationAction((AbstractCard) this));
            }
        } else if (playerNecromance < masterDeckAmt) {
            dupCheck = true;
        }
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        dupCheck = true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        dupCheck = true;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Gremory"));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractGameEffect) new HeartBuffEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
        int playerNecromance = 0;
        if (abstractPlayer.hasPower(Cemetery.POWER_ID)) {
            for (AbstractPower p : abstractPlayer.powers) {
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
        if (playerNecromance >= masterDeckAmt && !abstractPlayer.hasPower(GremoryPower.POWER_ID) && !abstractPlayer.hasPower(GremoryUsedPower.POWER_ID)) {
            addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new GremoryPower(abstractPlayer)));
        }
        dupCheck = true;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Gremory();
    }
}

