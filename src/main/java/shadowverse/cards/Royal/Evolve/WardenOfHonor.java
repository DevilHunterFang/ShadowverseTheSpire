package shadowverse.cards.Royal.Evolve;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.relics.KagemitsuSword;


public class WardenOfHonor
        extends CustomCard {
    public static final String ID = "shadowverse:WardenOfHonor";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WardenOfHonor");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WardenOfHonor.png";

    public WardenOfHonor() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Accelerate(this)) {
            setCostForTurn(0);
            this.type = CardType.POWER;
        } else {
            if (this.type == CardType.POWER) {
                setCostForTurn(2);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.type == CardType.POWER && this.costForTurn == 0) {
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DrawCardNextTurnPower(abstractPlayer,1),1));
        } else {
            this.upgrade();
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            addToBot(new GainBlockAction(abstractPlayer, this.block));
            AbstractCard copy = this.makeCopy();
            if(abstractPlayer.hasRelic(KagemitsuSword.ID)){
                copy.upgrade();
            }
            addToBot(new MakeTempCardInDiscardAction(copy,1));
        }
        if (EnergyPanel.getCurrentEnergy() < 4 && this.costForTurn > 0){
            addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WardenOfHonor();
    }
}

