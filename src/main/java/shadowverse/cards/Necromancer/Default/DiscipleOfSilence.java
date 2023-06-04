package shadowverse.cards.Necromancer.Default;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Necromancer;


public class DiscipleOfSilence
        extends CustomCard {
    public static final String ID = "shadowverse:DiscipleOfSilence";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DiscipleOfSilence");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DiscipleOfSilence.png";

    public DiscipleOfSilence() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("DiscipleOfSilence"));
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        for (AbstractMonster m :(AbstractDungeon.getCurrRoom()).monsters.monsters){
            if (m!=null && !m.isDeadOrEscaped() && !m.isDying && !m.halfDead){
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)m, -this.magicNumber), -this.magicNumber));
                if (!m.hasPower("Artifact"))
                    addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)abstractPlayer, (AbstractPower)new GainStrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DiscipleOfSilence();
    }
}

