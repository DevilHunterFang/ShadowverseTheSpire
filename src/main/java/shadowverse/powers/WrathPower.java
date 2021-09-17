package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;

public class WrathPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:WrathPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WrathPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WrathPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
        this.img = new Texture("img/powers/WrathPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
