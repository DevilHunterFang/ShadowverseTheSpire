package shadowverse.cards.Royal.Hero;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.DrawPileToHandAction_Tag;
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

    public void triggerOnGlowCheck() {
        if ((!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1)).hasTag(AbstractShadowversePlayer.Enums.HERO))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        int amount = 1;
        if (inDanger())
            amount++;
        addToBot(new DrawPileToHandAction_Tag(amount,AbstractShadowversePlayer.Enums.HERO,null));
        if ((AbstractDungeon.actionManager.cardsPlayedThisCombat.size() > 1 && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 2)).hasTag(AbstractShadowversePlayer.Enums.HERO))){
            AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat
                    .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                            .size() - 2);
            c.setCostForTurn(0);
            if (p.discardPile.group.contains(c)){
                addToBot(new DiscardToHandAction(c));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Windslasher();
    }
}

