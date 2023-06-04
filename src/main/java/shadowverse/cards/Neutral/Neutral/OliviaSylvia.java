package shadowverse.cards.Neutral.Neutral;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.cards.AbstractNeutralCard;
import shadowverse.cards.Neutral.Status.EvolutionPoint;

public class OliviaSylvia
        extends AbstractNeutralCard {
    public static final String ID = "shadowverse:OliviaSylvia";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OliviaSylvia");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OliviaSylvia.png";

    public OliviaSylvia() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 10;
        this.baseDamage = 0;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            upgradeMagicNumber(2);
        }
    }


    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        int amount = 0;
        for (AbstractCard c : p.exhaustPile.group) {
            if (c instanceof EvolutionPoint)
                amount++;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage = amount * this.magicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        int amount = 0;
        for (AbstractCard c : p.exhaustPile.group) {
            if (c instanceof EvolutionPoint)
                amount++;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage = amount * this.magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("OliviaSylvia"));
        addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        addToBot(new MakeTempCardInHandAction(new EvolutionPoint(), 2));
        int amount = 0;
        for (AbstractCard c : abstractPlayer.exhaustPile.group) {
            if (c instanceof EvolutionPoint)
                amount++;
        }
        calculateCardDamage(abstractMonster);
        addToBot(new VFXAction(abstractPlayer, new CleaveEffect(), 0.3F));
        addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new GainEnergyAction(amount));
    }


    public AbstractCard makeCopy() {
        return new OliviaSylvia();
    }
}

