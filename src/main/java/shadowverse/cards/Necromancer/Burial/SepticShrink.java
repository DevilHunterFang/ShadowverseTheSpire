package shadowverse.cards.Necromancer.Burial;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.MementoPower;


public class SepticShrink extends CustomCard {
    public static final String ID = "shadowverse:SepticShrink";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SepticShrink");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SepticShrink.png";

    public SepticShrink() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.burialCount;
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DrawCardAction(1));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SepticShrink"));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new BurialAction(1, new GainEnergyAction(1)));
        int attackAmt = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c != this && c.type == CardType.ATTACK)
                attackAmt++;
        }
        if (attackAmt >= 2) {
            if (abstractPlayer.hasPower(MementoPower.POWER_ID))
                addToBot(new GainEnergyAction(1));
        }
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
            int count = w.burialCount;
            if (count > 4) {
                addToBot(new DamageAllEnemiesAction(abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SepticShrink();
    }
}
