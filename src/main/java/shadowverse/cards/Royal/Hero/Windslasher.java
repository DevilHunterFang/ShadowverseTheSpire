package shadowverse.cards.Royal.Hero;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Windslasher extends CustomCard {
    public static final String ID = "shadowverse:Windslasher";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Windslasher.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;


    public Windslasher() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    public boolean inDanger() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= p.maxHealth / 4) {
            return true;
        }
        if (p instanceof AbstractShadowversePlayer) {
            if (((AbstractShadowversePlayer) p).wrathLastTurn > 0) {
                return true;
            }
        }
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                return true;
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.CURSE || (c.type == CardType.STATUS && !"shadowverse:EvolutionPoint".equals(c.cardID))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new FetchAction(p.drawPile, card -> hasTag(AbstractShadowversePlayer.Enums.HERO), 1, abstractCards -> {
            if (inDanger())
                for (AbstractCard c : abstractCards) {
                    c.setCostForTurn(0);
                }
        }));
        addToBot(new SelectCardsAction(p.discardPile.group,1,TEXT[0],false,card -> hasTag(AbstractShadowversePlayer.Enums.HERO),abstractCards ->
        {
            for (AbstractCard c : abstractCards) {
                c.setCostForTurn(0);
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Windslasher();
    }
}

