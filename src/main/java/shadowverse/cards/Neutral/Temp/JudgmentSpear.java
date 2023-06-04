package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;
import shadowverse.powers.JudgementSpearPower;

public class JudgmentSpear extends CustomCard {
    public static final String ID = "shadowverse:JudgmentSpear";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:JudgmentSpear");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/JudgmentSpear.png";

    public JudgmentSpear() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new JudgementSpearPower(AbstractDungeon.player,2,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new JudgmentSpear();
    }
}
