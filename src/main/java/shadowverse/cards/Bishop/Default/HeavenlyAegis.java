package shadowverse.cards.Bishop.Default;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.HeavenlyAegisPower;

public class HeavenlyAegis
        extends CustomCard implements AbstractNoCountDownAmulet {
    public static final String ID = "shadowverse:HeavenlyAegis";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HeavenlyAegis");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HeavenlyAegis.png";

    public HeavenlyAegis() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.MINION);
        this.baseDamage = 8;
        this.baseBlock = 8;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            upgradeBaseCost(3);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("HeavenlyAegis"));
        addToBot((AbstractGameAction)new VFXAction(new MiracleEffect()));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new HeavenlyAegisPower(p)));
        addToBot((AbstractGameAction)new PlaceAmulet(this.makeStatEquivalentCopy(),null));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new HeavenlyAegis();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {

    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {
        addToBot((AbstractGameAction) new GainBlockAction(AbstractDungeon.player,this.block));
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null && !m.isDeadOrEscaped()) {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
            addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {

    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }
}


