package shadowverse.cards.Vampire.Evolve;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

public class DestructiveSuccubus extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DestructiveSuccubus");
    public static final String ID = "shadowverse:DestructiveSuccubus";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DestructiveSuccubus.png";

    public DestructiveSuccubus() {
        this(0);
    }

    public DestructiveSuccubus(int upgrades) {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseBlock = 4;
        this.baseDamage = 30;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();

        int diff = this.costForTurn - this.cost;
        this.cost = 5-this.timesUpgraded;
        if (this.costForTurn > 0) {
            this.costForTurn = this.cost + diff;
        }

        if (this.costForTurn < 0) {
            this.costForTurn = 0;
        }

        this.upgradedCost = true;
    }

    @Override
    public boolean canUpgrade() {
        return this.timesUpgraded < 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("DestructiveSuccubus"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        if (p.stance.ID.equals(Vengeance.STANCE_ID) || p.hasPower(EpitaphPower.POWER_ID)) {
            addToBot(new GainBlockAction(p,p,this.block));
            addToBot(new GainBlockAction(p,p,this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DestructiveSuccubus();
    }
}
