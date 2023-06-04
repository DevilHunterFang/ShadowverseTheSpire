package shadowverse.cards.Witch.Earth2;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;


import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

public class Magisa extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Magisa");
    public static final String ID = "shadowverse:Magisa";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Magisa.png";

    public Magisa() {
        super("shadowverse:Magisa", NAME, "img/cards/Magisa.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.baseBlock = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Magisa"));
        if (abstractMonster != null)
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        addToBot((AbstractGameAction) new WaitAction(0.8F));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractMonster, (AbstractCreature) abstractPlayer, (AbstractPower) new VulnerablePower((AbstractCreature) abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        boolean powerExists = false;
        AbstractPower earthEssence = null;
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("shadowverse:EarthEssence")) {
                earthEssence = pow;
                powerExists = true;
                break;
            }
        }
        if (powerExists) {
            switch (earthEssence.amount) {
                case 1:
                    if (abstractPlayer instanceof AbstractShadowversePlayer) {
                        ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
                    }
                    addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, -1), -1));
                    break;

                case 2:
                    if (abstractPlayer instanceof AbstractShadowversePlayer) {
                        ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
                    }
                    addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                    addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, -2), -2));
                    break;

                case 3:
                    if (abstractPlayer instanceof AbstractShadowversePlayer) {
                        ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
                    }
                    addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                    addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractMonster, (AbstractCreature) abstractPlayer, (AbstractPower) new WeakPower((AbstractCreature) abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, -3), -3));
                    break;

                case 4:
                default:
                    if (abstractPlayer instanceof AbstractShadowversePlayer) {
                        ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
                    }
                    addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
                    addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractMonster, (AbstractCreature) abstractPlayer, (AbstractPower) new WeakPower((AbstractCreature) abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                    addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new InflameEffect((AbstractCreature) abstractPlayer), 1.0F));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new StrengthPower((AbstractCreature) abstractPlayer, 3), 3));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new DexterityPower((AbstractCreature) abstractPlayer, 3), 3));
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, -4), -4));
                    break;
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Magisa();
    }
}


