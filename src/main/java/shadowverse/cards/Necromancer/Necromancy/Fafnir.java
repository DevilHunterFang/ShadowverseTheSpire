package shadowverse.cards.Necromancer.Necromancy;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;

public class Fafnir extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Fafnir");
    public static final String ID = "shadowverse:Fafnir";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Fafnir.png";

    public Fafnir() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeBaseCost(3);
            upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Fafnir"));
        int playerNecromance = 0;
        if (abstractPlayer.hasPower(Cemetery.POWER_ID)) {
            for (AbstractPower p : abstractPlayer.powers) {
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new VFXAction(new WeightyImpactEffect(mo.hb.cX, mo.hb.cY)));
        }
        addToBot(new WaitAction(0.8F));
        addToBot(new NecromanceAction(playerNecromance, null,
                new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber * playerNecromance, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true)));
        addToBot(new MakeTempCardInDiscardAction(new Burn(), 1));
    }

    public AbstractCard makeCopy() {
        return new Fafnir();
    }
}
