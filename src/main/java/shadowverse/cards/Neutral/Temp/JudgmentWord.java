package shadowverse.cards.Neutral.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Witchcraft;


public class JudgmentWord
        extends CustomCard {
    public static final String ID = "shadowverse:JudgmentWord";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JudgmentWord");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JudgmentWord.png";

    public JudgmentWord() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("JudgmentWord"));
        if (abstractMonster.hasPower("Artifact")) {
            addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractPlayer, "Artifact"));
        } else {
            for (AbstractPower pow : abstractMonster.powers) {
                if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower") {
                    addToBot(new RemoveSpecificPowerAction(pow.owner, abstractPlayer, pow.ID));
                    break;
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new JudgmentWord();
    }
}

