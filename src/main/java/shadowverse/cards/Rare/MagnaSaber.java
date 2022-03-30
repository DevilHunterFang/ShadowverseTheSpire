package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.cards.AbstractVehicleCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.BearminatorPower;


public class MagnaSaber extends AbstractVehicleCard {
    public static final String ID = "shadowverse:MagnaSaber";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagnaSaber");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MagnaSaber.png";

    public MagnaSaber() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
        this.baseMagicNumber = 18;
        this.magicNumber = this.baseMagicNumber;
        this.predicate = card -> card.type == CardType.ATTACK && card.costForTurn <= 2;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
            upgradeMagicNumber(3);
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
        if (c.costForTurn <= 2 && c.type == CardType.ATTACK && !this.maneuver) {
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
            AbstractCard c = this.makeStatEquivalentCopy();
            c.freeToPlayOnce = true;
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c));
            this.cardsToPreview = null;
            applyPowers();
            this.maneuver = false;
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("MagnaSaber"));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction) new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        addToBot((AbstractGameAction) new SFXAction("ATTACK_HEAVY"));
        addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new CleaveEffect(), 0.1F));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MagnaSaber();
    }
}
