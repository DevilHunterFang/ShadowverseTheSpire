package shadowverse.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import shadowverse.cards.Vampire.Basic.RazoryClaw;

import java.util.ArrayList;
import java.util.List;

public class ShadowverseVampiresEvent extends AbstractImageEvent {
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("shadowverse:VampiresEvent");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String ACCEPT_BODY = DESCRIPTIONS[1];
    private static final String EXIT_BODY = DESCRIPTIONS[2];
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String ID = "shadowverse:VampiresEvent";
    private boolean hasVial;
    private List<String> bites;

    public ShadowverseVampiresEvent(){
        super(NAME, "test", "images/events/vampires.jpg");
        this.body = DESCRIPTIONS[0];
        this.bites = new ArrayList<>();
        this.hasVial = AbstractDungeon.player.hasRelic("Blood Vial");
        this.imageEventText.setDialogOption(OPTIONS[0], (AbstractCard)new RazoryClaw());
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(ACCEPT_BODY);
                        replaceAttacks();
                        if (!this.hasVial){
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)new BloodVial());
                        }
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
                this.imageEventText.updateBodyText(EXIT_BODY);
                AbstractDungeon.player.increaseMaxHp(10, true);
                this.screenNum = 2;
                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                this.imageEventText.clearRemainingOptions();
                return;
            case 1:
                openMap();
                return;
        }
        openMap();
    }

    private void replaceAttacks() {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
        int i;
        int amt=0;
        for (i = masterDeck.size() - 1; i >= 0; i--) {
            AbstractCard card = masterDeck.get(i);
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)){
                AbstractDungeon.player.masterDeck.removeCard(card);
                amt++;
            }
        }
        if (amt>0){
            for (i = 0; i < amt; i++) {
                RazoryClaw bite = new RazoryClaw();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)bite, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                this.bites.add(((AbstractCard)bite).cardID);
            }
        }
    }
}
