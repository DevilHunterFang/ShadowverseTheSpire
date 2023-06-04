package shadowverse.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import shadowverse.cards.Neutral.Curse.Geass;
import shadowverse.relics.GeassRelic;

public class LelouchCollaboration extends AbstractEvent {

    public static final String ID = "shadowverse:LelouchCollaboration";

    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("shadowverse:LelouchCollaboration");

    public static final String NAME = eventStrings.NAME;

    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;

    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private Texture fgImg = ImageMaster.loadImage("img/event/LelouchCollaboration.png");
    private static final String HEAL_MSG = DESCRIPTIONS[0];

    private static final String FIGHT_MSG = DESCRIPTIONS[1];

    private int screenNum = 0;

    public LelouchCollaboration() {
        this.body = DESCRIPTIONS[2];
        this.roomEventText.addDialogOption(OPTIONS[0]);
        this.roomEventText.addDialogOption(OPTIONS[1] + OPTIONS[2], CardLibrary.getCopy(Geass.ID));
        (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.EVENT;
        this.hasDialog = true;
        this.hasFocus = true;
    }

    public void update() {
        super.update();
        if (!RoomEventDialog.waitForInput)
            buttonEffect(this.roomEventText.getSelectedOption());
    }

    protected void buttonEffect(int buttonPressed) {
        Geass geass;
        switch (buttonPressed) {
            case 0:
                if (this.screenNum == 0) {
                    (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("shadowverse:Lelouch");
                    this.roomEventText.updateBodyText(FIGHT_MSG);
                    this.roomEventText.updateDialogOption(0, OPTIONS[3]);
                    this.roomEventText.removeDialogOption(1);
                    AbstractEvent.logMetric("LelouchCollaboration", "Fought Lelouch");
                    this.screenNum += 2;
                } else if (this.screenNum == 1) {
                    openMap();
                } else if (this.screenNum == 2) {
                    if (Settings.isDailyRun) {
                        AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(25));
                    } else {
                        AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(20, 30));
                    }
                    if (AbstractDungeon.player.hasRelic(GeassRelic.ID)) {
                        AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new Circlet());
                    } else {
                        AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new GeassRelic());
                    }
                    enterCombat();
                }
                return;
            case 1:
                geass = new Geass();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)geass, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                this.roomEventText.updateBodyText(HEAL_MSG);
                this.roomEventText.updateDialogOption(0, OPTIONS[4]);
                this.roomEventText.removeDialogOption(1);
                this.screenNum = 1;
                return;
        }
    }


    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(this.fgImg, 0.0F, 600.0F * Settings.scale, 600.0F * Settings.scale, 600.0F * Settings.scale);
    }

    public void dispose() {
        super.dispose();
        if (this.fgImg != null) {
            this.fgImg.dispose();
            this.fgImg = null;
        }
    }
}
