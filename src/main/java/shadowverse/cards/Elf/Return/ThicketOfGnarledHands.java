package shadowverse.cards.Elf.Return;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.Shadowverse;
import shadowverse.action.BounceAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


public class ThicketOfGnarledHands
        extends CustomCard {
    public static final String ID = "shadowverse:ThicketOfGnarledHands";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ThicketOfGnarledHands");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ThicketOfGnarledHands.png";

    public ThicketOfGnarledHands() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(4);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void triggerOnGlowCheck() {
        if (Shadowverse.Accelerate(this)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        boolean hasAttack = false;
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            for (AbstractCard c : p.discardPile.group) {
                if (c.type == CardType.ATTACK || c.type == CardType.POWER)
                    hasAttack = true;
            }
            if (!hasAttack) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                canUse = false;
            }
        }
        return canUse;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            addToBot(new BounceAction(1));
            addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer, this.damage), AbstractGameAction.AttackEffect.POISON));
        } else {
            int rand = AbstractDungeon.cardRandomRng.random(AbstractDungeon.actionManager.cardsPlayedThisCombat.size());
            int rand2 = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - rand;
            addToBot(new VFXAction(new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Color.GREEN, ShockWaveEffect.ShockWaveType.ADDITIVE)));
            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(rand*this.magicNumber, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
            addToBot(new GainBlockAction(abstractPlayer,rand2*this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ThicketOfGnarledHands();
    }
}

