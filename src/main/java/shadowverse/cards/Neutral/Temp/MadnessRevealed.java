package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Vampire;
import shadowverse.powers.HealNextTurnPower;

public class MadnessRevealed extends CustomCard {
    public static final String ID = "shadowverse:MadnessRevealed";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MadnessRevealed");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MadnessRevealed.png";

    public MadnessRevealed() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        addToBot((AbstractGameAction)new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,4));
        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new HealNextTurnPower(AbstractDungeon.player,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new MadnessRevealed();
    }
}
