package shadowverse.cards.Vampire.Evolve;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import shadowverse.characters.Vampire;

public class Seox extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Seox");
    public static final String ID = "shadowverse:Seox";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Seox.png";


    public Seox() {
        this(0);
    }

    public Seox(int upgrades) {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
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
        int count = 0;
        if (this.magicNumber <= 0) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_SSA"));
            count = 6;
        } else if (this.magicNumber <= 3) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_SA"));
            count = 4;
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
            count = 2;
        }
        for (int i = 0; i < count; i++){
            if (m!=null)
                addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + Math.max(0, this.magicNumber - 6) + cardStrings.EXTENDED_DESCRIPTION[1] + Math.max(0, this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + Math.max(0, this.magicNumber - 6) + cardStrings.EXTENDED_DESCRIPTION[1] + Math.max(0, this.magicNumber) + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Seox();
    }
}
