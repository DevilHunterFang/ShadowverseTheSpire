package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.Royal;

public class ExterminusWeapon extends CustomCard {
    public static final String ID = "shadowverse:ExterminusWeapon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ExterminusWeapon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ExterminusWeapon.png";

    public ExterminusWeapon(int upgrades) {
        this();
        for (int i = 0; i < upgrades; i++) {
            this.upgrade();
        }
    }

    public ExterminusWeapon() {
        super(ID, NAME, IMG_PATH, 6, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 48;
        this.isMultiDamage = true;
        this.selfRetain = true;
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return this.cost > 0;
    }

    @Override
    public void upgrade() {
        this.upgradeBaseCost(this.cost - 1);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }


    @Override
    public void onRetained() {
        super.onRetained();
        this.upgrade();
        this.superFlash();
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExterminusWeapon();
    }
}

