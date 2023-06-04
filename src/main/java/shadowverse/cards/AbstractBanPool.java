package shadowverse.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.Shadowverse;
import shadowverse.helper.ViewBanCardScreen;

import java.util.ArrayList;

public abstract class AbstractBanPool extends CustomCard {
    public static final String DESCRIPTION;
    public int code;

    public AbstractBanPool(String name, String img, CardColor color, int code) {
        super("shadowverse:BanCardView", name, img, -2, DESCRIPTION, CardType.ATTACK, color, CardRarity.SPECIAL, CardTarget.NONE);
        this.code = code;
    }

    public ArrayList<BanGroup> getPool() {
        return new ArrayList<>();
    }

    abstract public int getGroupCount();

    abstract public int getActiveCount();

    public void showGroup() {
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.closeCurrentScreen();
        if (AbstractDungeon.getCurrRoom().rewardTime) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }

        if (Shadowverse.banCardGroupScreen == null) {
            Shadowverse.banCardGroupScreen = new ViewBanCardScreen();
        }

        Shadowverse.banCardGroupScreen.open(getPool().get(this.code).group);
    }

    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    abstract public AbstractCard makeCopy();

    abstract public AbstractBanPool copy(int code);

    static {
        DESCRIPTION = CardCrawlGame.languagePack.getCardStrings("shadowverse:BanCardView").DESCRIPTION;
    }
}
