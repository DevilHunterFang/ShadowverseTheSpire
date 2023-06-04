package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.orbs.Quickblader;

public class ErikasSleight extends CustomCard {
    public static final String ID = "shadowverse:ErikasSleight";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ErikasSleight.png";

    public ErikasSleight() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Quickblader()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new ErikasSleight();
    }
}

