package shadowverse.cards.Royal.Evolve;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import shadowverse.characters.Royal;

public class Seofon extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Seofon");
    public static final String ID = "shadowverse:Seofon";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Seofon.png";
    public boolean triggered;

    public Seofon() {
        this(0);
    }

    public Seofon(int upgrades) {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.triggered = false;
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();
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
        addToBot(new DrawCardAction(p, 1));
        if (this.magicNumber <= 3) {
            this.addToBot(new ApotheosisAction());
        }
        if (!this.triggered && this.magicNumber <= 0) {
            this.triggered = true;
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
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
        return new Seofon();
    }
}
