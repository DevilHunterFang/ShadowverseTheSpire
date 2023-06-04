package shadowverse.cards.Vampire.Vengeance;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

public class DiabolicDrain extends CustomCard {
    public static final String ID = "shadowverse:DiabolicDrain";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DiabolicDrain");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DiabolicDrain.png";

    public DiabolicDrain() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID) || AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)) {
            this.setCostForTurn(0);
            this.isCostModified = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) monster, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction) new HealAction(p, p, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DiabolicDrain();
    }
}
