package shadowverse.events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;

public class Crossover extends AbstractImageEvent {
    public static final String ID = "Crossover";

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Crossover");

    public static final String NAME = eventStrings.NAME;

    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;

    public static final String[] OPTIONS = eventStrings.OPTIONS;

    private static final String INTRO_BODY_M = DESCRIPTIONS[0];

    private static final String ACCEPT_BODY = DESCRIPTIONS[2];

    private static final String EXIT_BODY = DESCRIPTIONS[3];

    private static final float HP_DRAIN = 0.3F;

    private int screenNum = 0, hpLoss = 0;

    public Crossover() {
        super(NAME, "test", "img/event/Crossover.jpg");
        this.body = INTRO_BODY_M;
        this.hpLoss = MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.1F);
        if (this.hpLoss >= AbstractDungeon.player.maxHealth) {
            this.hpLoss = AbstractDungeon.player.maxHealth - 1;
        }
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.imageEventText.setDialogOption(OPTIONS[3] + this.hpLoss + OPTIONS[1]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[0] + this.hpLoss + OPTIONS[1]);
        }
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_GHOSTS");
        }
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(ACCEPT_BODY);
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)null, this.hpLoss, DamageInfo.DamageType.HP_LOSS));
                        becomeGhost();
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
                logMetricIgnored("Crossover");
                this.imageEventText.updateBodyText(EXIT_BODY);
                this.screenNum = 2;
                this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                this.imageEventText.clearRemainingOptions();
                return;
            case 1:
                openMap();
                return;
        }
        openMap();
    }

    private void becomeGhost() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, RelicLibrary.getRelic("shadowverse:AlterplaneArbiter").makeCopy());
    }
}
