package shadowverse.cards.Royal.Minion;

import basemod.abstracts.CustomCard;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.Royal;
import shadowverse.orbs.QueenHemera;
import shadowverse.orbs.QueenMagnus;

import java.util.ArrayList;

public class MonochromeEndgame extends CustomCard {
    public static final String ID = "shadowverse:MonochromeEndgame";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MonochromeEndgame");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MonochromeEndgame.png";

    public MonochromeEndgame() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new QueenHemera()));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new QueenMagnus()));
        } else {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new QueenHemera_Card());
            stanceChoices.add(new QueenMagnus_Card());
            addToBot(new ChooseOneAction(stanceChoices));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new MonochromeEndgame();
    }
}

