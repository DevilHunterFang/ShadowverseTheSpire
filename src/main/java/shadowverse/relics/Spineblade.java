package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Spineblade extends CustomRelic {
    public static final String ID = "shadowverse:Spineblade";
    public static final String IMG = "img/relics/Spineblade.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Spineblade_Outline.png";

    public Spineblade() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void atTurnStart() {
        addToBot(new ScryAction(4));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Spineblade();
    }
}
