package shadowverse.cards.Vampire.Evolve;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Vampire;
import shadowverse.relics.KagemitsuSword;


public class Signa
        extends CustomCard {
    public static final String ID = "shadowverse:Signa";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Signa");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Signa.png";

    public Signa() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 9;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new EvolutionPoint();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Signa"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        if (this.upgraded) {
            int count = 0;
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c instanceof EvolutionPoint)
                    count++;
            }
            if (count > 2) {
                addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            }
            if (count > 6) {
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
                addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
            }
            this.degrade();
            if (p.hasRelic(KagemitsuSword.ID)) {
                this.upgrade();
            }
        }
    }


    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof EvolutionPoint && !this.upgraded) {
            this.upgrade();
            addToBot(new SFXAction("Signa_Eff"));
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Signa();
    }
}

