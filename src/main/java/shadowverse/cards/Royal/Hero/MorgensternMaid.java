package shadowverse.cards.Royal.Hero;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class MorgensternMaid extends CustomCard {
    public static final String ID = "shadowverse:MorgensternMaid";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MorgensternMaid.png";


    public MorgensternMaid() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.NONE);
        this.baseBlock = 10;
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDamage = 5;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            upgradeMagicNumber(1);
            upgradeDamage(2);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new MorgensternMaid1(this.upgraded));
        stanceChoices.add(new MorgensternMaid2(this.upgraded));
        stanceChoices.add(new MorgensternMaid3(this.upgraded));
        addToBot((AbstractGameAction) new ChooseOneAction(stanceChoices));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MorgensternMaid();
    }
}
