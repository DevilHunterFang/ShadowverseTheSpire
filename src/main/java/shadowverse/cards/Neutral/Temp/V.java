package shadowverse.cards.Neutral.Temp;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import shadowverse.cards.AbstractVehicleCard;
import shadowverse.characters.Royal;


public class V
        extends AbstractVehicleCard {
    public static final String ID = "shadowverse:V";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:V");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/V.png";

    public V() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.baseBlock = 8;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.predicate = card -> card.type == CardType.ATTACK && card.costForTurn >= 1;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeDamage(4);
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (!this.maneuver) {
            canUse = false;
        }
        return canUse;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        if (!this.maneuver) {
            glow = false;
        }
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.costForTurn >= 1 && c.type == CardType.ATTACK && !this.maneuver) {
            this.maneuver = true;
            flash();
            addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            this.cardsToPreview = c.makeStatEquivalentCopy();
            applyPowers();
        }
    }

    @Override
    public void triggerOnExhaust() {
        if (this.cardsToPreview != null) {
            AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
            c.setCostForTurn(0);
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c));
            this.cardsToPreview = null;
            applyPowers();
            this.maneuver = false;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int rnd1 = AbstractDungeon.cardRandomRng.random(3);
        int rnd2 = AbstractDungeon.cardRandomRng.random(3);
        while (rnd1==rnd2){
            rnd2 = AbstractDungeon.cardRandomRng.random(3);
        }
        int drawAmt = 1;
        if (this.upgraded){
            drawAmt = 2;
        }
        AbstractGameAction[] actions = {
                (AbstractGameAction) new DrawCardAction(drawAmt),
                (AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, DamageInfo.createDamageMatrix(drawAmt*2+2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true),
                (AbstractGameAction) new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT),
                (AbstractGameAction) new GainBlockAction(p, this.block)
        };
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(actions[rnd1]);
        addToBot(actions[rnd2]);
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new V();
    }
}

