package shadowverse.cards.Royal.Minion;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.EleganceInActionAction;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.Royal;

public class EleganceInAction extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:EleganceInAction";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EleganceInAction.png";


    public EleganceInAction() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE,1);
        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 2;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new DrawCardAction(1, new EleganceInActionAction(this)));
        }
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(1, new EleganceInActionAction(this)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new EleganceInAction();
    }
}




