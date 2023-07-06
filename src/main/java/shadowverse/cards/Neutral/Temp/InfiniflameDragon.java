package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class InfiniflameDragon
        extends CustomCard {
    public static final String ID = "shadowverse:InfiniflameDragon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:InfiniflameDragon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/InfiniflameDragon.png";

    public InfiniflameDragon() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 100;
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(4);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new VFXAction(new InflameEffect(abstractPlayer)));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, 50, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo.hasPower(MinionPower.POWER_ID)) {
                addToBot(new SuicideAction(mo));
                addToBot(new VFXAction(new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
                addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }
        }
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c.type == CardType.ATTACK) {
                addToBot(new VFXAction(new ClawEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
                addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                addToBot(new ExhaustSpecificCardAction(c,abstractPlayer.hand));
            }
        }
        if (abstractMonster != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
            addToBot(new WaitAction(0.6F));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new VFXAction(new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
            addToBot(new WaitAction(0.6F));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new InfiniflameDragon();
    }
}

