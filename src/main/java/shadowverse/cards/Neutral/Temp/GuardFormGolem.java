package shadowverse.cards.Neutral.Temp;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


public class GuardFormGolem
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:GuardFormGolem";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GuardFormGolem");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GuardFormGolem.png";

    public GuardFormGolem() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF,2);
        this.baseBlock = 12;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    @Override
    public void update() {
        if(this.costForTurn!=0){
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                    Shadowverse.Enhance(2)) {
                setCostForTurn(2);
            } else {
                setCostForTurn(1);
            }
        }
        super.update();
    }

    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block * 2));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GuardFormGolem();
    }
}

