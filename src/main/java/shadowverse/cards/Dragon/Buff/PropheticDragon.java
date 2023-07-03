package shadowverse.cards.Dragon.Buff;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.Dragon;
import shadowverse.powers.OverflowPower;


public class PropheticDragon
        extends CustomCard {
    public static final String ID = "shadowverse:PropheticDragon";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PropheticDragon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/PropheticDragon.png";

    public PropheticDragon() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 10;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this) && AbstractDungeon.player.hasPower(DexterityPower.POWER_ID) && AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount >= 2) {
            setCostForTurn(0);
            this.type = CardType.SKILL;
        } else {
            if (this.type == CardType.SKILL) {
                setCostForTurn(4);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new OverflowPower(abstractPlayer, 1)));
            if (abstractPlayer.hasPower(OverflowPower.POWER_ID)) {
                TwoAmountPower p = (TwoAmountPower) abstractPlayer.getPower(OverflowPower.POWER_ID);
                if (p.amount2 > 0) {
                    addToBot(new DrawCardAction(1));
                }
            }
        } else {
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            addToBot(new GainBlockAction(abstractPlayer, this.block));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new PropheticDragon();
    }
}

