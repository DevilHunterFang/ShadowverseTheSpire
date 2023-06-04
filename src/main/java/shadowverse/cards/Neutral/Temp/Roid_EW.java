package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Nemesis;


public class Roid_EW
        extends CustomCard {
    public static final String ID = "shadowverse:VRoid_EW";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Roid_EW");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Roid_EW.png";

    public Roid_EW() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 28;
        this.baseBlock = 18;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m != null && !m.isDeadOrEscaped() && !m.isDying && !m.halfDead) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) abstractPlayer, (AbstractPower) new StrengthPower((AbstractCreature) m, -this.magicNumber), -this.magicNumber));
                if (!m.hasPower("Artifact"))
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) abstractPlayer, (AbstractPower) new GainStrengthPower((AbstractCreature) m, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Roid_EW();
    }
}

