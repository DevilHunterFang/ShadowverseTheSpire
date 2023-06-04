package shadowverse.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import shadowverse.cards.Royal.Default.Alyaska;
import shadowverse.cards.Royal.Default.MasterDealer;

public class SellCard extends AbstractImageEvent {
    public static final String ID = "SellCard";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String DIALOG_2;
    private static final String DIALOG_3;
    private SellCard.CUR_SCREEN screen;
    private AbstractCard offeredCard;
    private boolean cardSelect;

    public SellCard() {
        super(NAME, DIALOG_1, "img/event/SellCard.png");
        this.screen = SellCard.CUR_SCREEN.INTRO;
        this.offeredCard = null;
        this.cardSelect = false;
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }


    public boolean hasAlyaska() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean has = false;
        for (AbstractCard c : p.masterDeck.group) {
            if (c instanceof Alyaska || c instanceof MasterDealer) {
                has = true;
            }
        }
        return has;
    }

    @Override
    public void update() {
        super.update();
        if (this.cardSelect && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.offeredCard = AbstractDungeon.gridSelectScreen.selectedCards.remove(0);
            switch (this.offeredCard.rarity) {
                case CURSE:
                    int damage = AbstractDungeon.player.maxHealth / 10;
                    logMetricCardRemovalAndDamage("SellCard", "Offered Curse", this.offeredCard, damage);
                    break;
                case BASIC:
                    logMetricCardRemoval("SellCard", "Offered Basic", this.offeredCard);
                    break;
                case COMMON:
                    logMetricCardRemoval("SellCard", "Offered Common", this.offeredCard);
                    break;
                case SPECIAL:
                    logMetricCardRemoval("SellCard", "Offered Special", this.offeredCard);
                    break;
                case UNCOMMON:
                    logMetricCardRemoval("SellCard", "Offered Uncommon", this.offeredCard);
                    break;
                case RARE:
                    logMetricCardRemoval("SellCard", "Offered Rare", this.offeredCard);
                    break;
                default:
            }

            this.setReward(this.offeredCard.rarity);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this.offeredCard, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
            AbstractDungeon.player.masterDeck.removeCard(this.offeredCard);
            this.imageEventText.updateDialogOption(0, OPTIONS[4]);
            this.screen = SellCard.CUR_SCREEN.COMPLETE;
            this.imageEventText.clearRemainingOptions();
            this.cardSelect = false;
        }

    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                this.imageEventText.updateBodyText(DIALOG_2);
                this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                if (hasAlyaska()) {
                    this.imageEventText.setDialogOption(OPTIONS[2], !hasAlyaska());
                } else {
                    this.imageEventText.setDialogOption(OPTIONS[3], !hasAlyaska());
                }
                this.screen = SellCard.CUR_SCREEN.CHOOSE;
                break;
            case CHOOSE:
                switch (buttonPressed) {
                    case 0:
                        if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).size() > 0) {
                            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[5], false, false, false, true);
                            this.cardSelect = true;
                        } else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                            this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                            this.screen = SellCard.CUR_SCREEN.COMPLETE;
                            this.imageEventText.clearRemainingOptions();
                        }
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[10]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.loadImage("img/event/SellCard2.png");
                        this.screen = SellCard.CUR_SCREEN.COMPLETE;
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, RelicLibrary.getRelic("shadowverse:ValhoreanDealer").makeCopy());
                        break;
                    default:
                }
                break;
            case COMPLETE:
                this.openMap();
                break;
            default:
        }

    }

    private void setReward(AbstractCard.CardRarity rarity) {
        String dialog = DIALOG_3;
        int goldReward = 0;
        switch (rarity) {
            case CURSE:
                dialog = dialog + DESCRIPTIONS[3];
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
                CardCrawlGame.sound.play("BLUNT_FAST");
                int damage = AbstractDungeon.player.maxHealth / 10;
                AbstractDungeon.player.damage(new DamageInfo(null, damage));
                break;
            case BASIC:
                dialog = dialog + DESCRIPTIONS[4];
                break;
            case COMMON:
                goldReward = 20;
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                AbstractDungeon.player.gainGold(goldReward);
                dialog = dialog + DESCRIPTIONS[5];
                break;
            case SPECIAL:
                goldReward = 50;
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                AbstractDungeon.player.gainGold(goldReward);
                dialog = dialog + DESCRIPTIONS[6];
                break;
            case UNCOMMON:
                goldReward = 80;
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                AbstractDungeon.player.gainGold(goldReward);
                dialog = dialog + DESCRIPTIONS[7];
                break;
            case RARE:
                goldReward = 150;
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                AbstractDungeon.player.gainGold(goldReward);
                dialog = dialog + DESCRIPTIONS[8];
            default:
        }
        this.imageEventText.updateBodyText(dialog);
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("shadowverse:SellCard");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        DIALOG_2 = DESCRIPTIONS[1];
        DIALOG_3 = DESCRIPTIONS[2];
    }

    private enum CUR_SCREEN {
        INTRO,
        CHOOSE,
        COMPLETE;

        private CUR_SCREEN() {
        }
    }
}
