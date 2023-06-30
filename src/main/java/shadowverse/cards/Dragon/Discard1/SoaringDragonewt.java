package shadowverse.cards.Dragon.Discard1;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DiscardRandomMinimumAction;
import shadowverse.action.NaturalDiscardAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.relics.KagemitsuSword;


public class SoaringDragonewt
        extends CustomCard {
    public static final String ID = "shadowverse:SoaringDragonewt";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SoaringDragonewt");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SoaringDragonewt.png";

    public SoaringDragonewt() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.SKILL) {
            this.upgrade();
            this.superFlash();
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

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SoaringDragonewt"));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DiscardRandomMinimumAction(abstractPlayer, abstractPlayer, 1));
        addToBot(new DrawCardAction(1));
        if (upgraded) {
            addToBot(new DiscardRandomMinimumAction(abstractPlayer, abstractPlayer, 1));
            addToBot(new DrawCardAction(1));
            this.degrade();
            if (abstractPlayer.hasRelic(KagemitsuSword.ID)) {
                this.upgrade();
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SoaringDragonewt();
    }
}

