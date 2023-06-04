package shadowverse.cards.Elf.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import shadowverse.characters.Elf;
import shadowverse.powers.TweyenPower;

public class Tweyen extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tweyen");
    public static final String ID = "shadowverse:Tweyen";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Tweyen.png";

    public Tweyen() {
        this(0);
    }

    public Tweyen(int upgrades) {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        if (this.magicNumber > 0) {
            upgradeMagicNumber(-1);
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return this.magicNumber > 0;
    }

    @Override
    public void atTurnStart() {
        if (this.baseMagicNumber > 0) {
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.magicNumber <= 0) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_SSA"));
        } else if (this.magicNumber <= 3) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_SA"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m, p, new TweyenPower(m)));
        if (this.magicNumber <= 0) {
            addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            for (int i = 0; i < 5; i++) {
                if (m != null)
                    addToTop((AbstractGameAction) new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY),0.2F));
            }
        }
        if (this.magicNumber <= 3) {
            addToBot(new ArmamentsAction(true));
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        }
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + Math.max(0, this.magicNumber - 3) + cardStrings.EXTENDED_DESCRIPTION[1] + Math.max(0, this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + Math.max(0, this.magicNumber - 3) + cardStrings.EXTENDED_DESCRIPTION[1] + Math.max(0, this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tweyen();
    }
}
