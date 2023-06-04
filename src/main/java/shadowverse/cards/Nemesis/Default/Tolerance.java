package shadowverse.cards.Nemesis.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.characters.Nemesis;


public class Tolerance extends CustomCard {
    public static final String ID = "shadowverse:Tolerance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tolerance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Tolerance.png";
    private int costToReduce;

    public Tolerance() {
        super(ID, NAME, IMG_PATH, 20, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 63;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(7);
            upgradeMagicNumber(1);
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK) {
            flash();
            this.costToReduce += c.costForTurn + 1;
        }
    }

    @Override
    public void onRetained() {
        this.superFlash();
        this.modifyCostForCombat(-this.costToReduce);
    }

    public void onMoveToDiscard() {
        this.costToReduce = 0;
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Tolerance"));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new VulnerablePower(abstractMonster, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractMonster, abstractPlayer, (AbstractPower) new WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber));
        if (abstractMonster != null)
            addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new VerticalImpactEffect(abstractMonster.hb.cX + abstractMonster.hb.width / 4.0F, abstractMonster.hb.cY - abstractMonster.hb.height / 4.0F)));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.cost = 20;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Tolerance();
    }
}

