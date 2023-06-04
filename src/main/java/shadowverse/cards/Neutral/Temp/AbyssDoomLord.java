package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.AbyssDoomLordPower;


public class AbyssDoomLord
        extends CustomCard {
    public static final String ID = "shadowverse:AbyssDoomLord";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbyssDoomLord");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AbyssDoomLord.png";

    public AbyssDoomLord() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 80;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(20);
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToTop((AbstractGameAction) new ExhaustAction(9, false, true, true));
        addToBot((AbstractGameAction) new SFXAction("AbyssDoomLord"));
        addToBot((AbstractGameAction) new LoseHPAction(abstractPlayer, abstractPlayer, abstractPlayer.currentHealth - 1));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new StrengthPower(abstractPlayer, 3), 3));
        addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new AbyssDoomLordPower(abstractPlayer, 1), 1));
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) abstractPlayer, DamageInfo.createDamageMatrix((abstractPlayer.currentHealth - 1) * 2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new AbyssDoomLord();
    }
}

