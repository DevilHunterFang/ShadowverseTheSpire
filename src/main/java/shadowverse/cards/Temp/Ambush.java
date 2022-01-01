package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Elf;
import shadowverse.characters.Royal;

public class Ambush extends CustomCard {
    public static final String ID = "shadowverse:Ambush";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ambush");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/VeiledReckoning.png";

    public Ambush() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
    }


    public void upgrade() {
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
    }

    public AbstractCard makeCopy() {
        return new Ambush();
    }
}
