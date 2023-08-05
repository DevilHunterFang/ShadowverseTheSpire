package shadowverse.cards.Dragon.Buff;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import shadowverse.cards.Witch.AbstractAccelerateCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;


public class Joe
        extends AbstractAccelerateCard {
    public static final String ID = "shadowverse:Joe";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Joe");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Joe.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;


    public Joe() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.ALL, 0, CardType.POWER);
        this.baseBlock = 15;
        this.baseDamage = 20;
        this.baseMagicNumber = 28;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            upgradeDamage(5);
            upgradeMagicNumber(4);
        }
    }

    @Override
    public void baseUse(AbstractPlayer abstractPlayer, AbstractMonster m) {
        addToBot(new SFXAction("Joe"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 4) {
            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        }
        if (abstractPlayer.hasPower(DexterityPower.POWER_ID) && abstractPlayer.getPower(DexterityPower.POWER_ID).amount >= 6) {
            addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void accUse(AbstractPlayer abstractPlayer, AbstractMonster m) {
        addToBot(new SFXAction("Joe_Acc"));
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, true, card -> {
            return true;
        }, abstractCards -> {
            for (AbstractCard c : abstractCards) {
                addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
                addToBot(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, true));
                addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DrawCardNextTurnPower(abstractPlayer, 1), 1));
                if (c.hasTag(AbstractShadowversePlayer.Enums.ACADEMIC)) {
                    addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DrawCardNextTurnPower(abstractPlayer, 1), 1));
                }
            }
        }));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, 1), 1));
        addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Joe();
    }
}

