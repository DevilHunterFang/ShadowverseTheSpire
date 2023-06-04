package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.BlackArtifact2;


public class WhiteArtifactPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:WhiteArtifactPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WhiteArtifactPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WhiteArtifactPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/WhiteArtifactPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw() {
        flash();
        boolean hasPlayedBlack2 = false;
        for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof BlackArtifact2){
                hasPlayedBlack2 = true;
                break;
            }
        }
        if (hasPlayedBlack2){
            addToBot((AbstractGameAction)new GainBlockAction(this.owner,this.amount*2));
        }else {
            addToBot((AbstractGameAction)new GainBlockAction(this.owner,this.amount));
        }
    }


}

