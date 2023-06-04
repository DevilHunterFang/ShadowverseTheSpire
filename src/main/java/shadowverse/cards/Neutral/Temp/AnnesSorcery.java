package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


public class AnnesSorcery
        extends CustomCard {
    public static final String ID = "shadowverse:AnnesSorcery";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AnnesSorcery");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AnnesSorcery.png";

    public AnnesSorcery() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
        this.exhaust = true;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void applyPowers() {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            if (w.mysteriaCount > 0) {
                int realBaseDamage = this.baseDamage;
                this.baseDamage = w.mysteriaCount * this.baseDamage;
                super.applyPowers();
                this.baseDamage = realBaseDamage;
                this.isDamageModified = (this.damage != this.baseDamage);
            }
        } else {
            super.applyPowers();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
        int realBaseDamage = this.baseDamage;
        this.baseDamage = w.mysteriaCount * this.baseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("AnnesSorcery"));
        addToBot(new WaitAction(0.8F));
        addToBot(new VFXAction((AbstractGameEffect) new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        calculateCardDamage(abstractMonster);
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AnnesSorcery();
    }
}


