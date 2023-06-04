package shadowverse.cards.Royal.Festive;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.MinionSummonAction;
import shadowverse.cards.Neutral.Temp.GlitteringGold;
import shadowverse.characters.Royal;
import shadowverse.orbs.*;

public class NobleShieldmaiden extends CustomCard {
    public static final String ID = "shadowverse:NobleShieldmaiden";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NobleShieldmaiden.png";
    public static final int ACCELERATE = 1;

    public NobleShieldmaiden() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseBlock = 20;
        this.cardsToPreview = new GlitteringGold();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")+"_Acc"));
            addToBot(new MakeTempCardInHandAction(new GlitteringGold()));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new SteelcladKnight()));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new FrontguardGeneral()));
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(1);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(3);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    @Override
    public AbstractCard makeCopy() {
        return new NobleShieldmaiden();
    }
}
