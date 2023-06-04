package shadowverse.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

public class ViewBanCardOption {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public static Texture BAN_CARD_VIEW;
    private Hitbox hb;
    private float angle;
    private float rotateTimer;

    public ViewBanCardOption() {
        this.hb = new Hitbox(64.0F * Settings.scale, 64.0F * Settings.scale);
        this.hb.move((float) Settings.WIDTH - (64.0F * Settings.scale + 10.0F * Settings.scale) * 4.0F + 32.0F * Settings.scale, (float) Settings.HEIGHT - 32.0F * Settings.scale);
    }

    public void unhoverHitboxes() {
        this.hb.unhover();
    }

    public void update() {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                this.rotateTimer += Gdx.graphics.getDeltaTime() * 4.0F;
                this.angle = MathHelper.angleLerpSnap(this.angle, MathUtils.sin(this.rotateTimer) * 15.0F);
            } else if (this.hb.hovered) {
                this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
            } else {
                this.angle = MathHelper.angleLerpSnap(this.angle, 0.0F);
            }

            if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.COMBAT_REWARD && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DEATH && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.VICTORY && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.BOSS_REWARD && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SHOP && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MAP && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.HAND_SELECT && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.CARD_REWARD) {
                this.hb.hovered = false;
            } else {
                this.hb.update();
            }

            boolean clickedDeckButton = this.hb.hovered && InputHelper.justClickedLeft;
            if (clickedDeckButton && !CardCrawlGame.isPopupOpen) {
                if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                    AbstractDungeon.closeCurrentScreen();
                    this.openScreen();
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                } else if (!AbstractDungeon.isScreenUp) {
                    this.openScreen();
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                    AbstractDungeon.screenSwap = false;
                    if (AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                        AbstractDungeon.previousScreen = null;
                    }

                    AbstractDungeon.closeCurrentScreen();
                    CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
//                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
//                    AbstractDungeon.deathScreen.hide();
//                    this.openScreen();
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
//                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
//                    AbstractDungeon.bossRelicScreen.hide();
//                    this.openScreen();
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
                    AbstractDungeon.overlayMenu.cancelButton.hide();
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                    this.openScreen();
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                    this.openScreen();
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {

                } else if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MAP) {
                    if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS) {
                        if (AbstractDungeon.previousScreen != null) {
                            AbstractDungeon.screenSwap = true;
                        }

                        AbstractDungeon.closeCurrentScreen();
                        this.openScreen();
                    } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
//                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
//                        AbstractDungeon.dynamicBanner.hide();
//                        this.openScreen();
                    } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
                        AbstractDungeon.gridSelectScreen.hide();
                        this.openScreen();
                    } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
                        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
                        this.openScreen();
                    }
                } else {
                    if (AbstractDungeon.previousScreen != null) {
                        AbstractDungeon.screenSwap = true;
                    }

                    AbstractDungeon.closeCurrentScreen();
                    this.openScreen();
                }

                InputHelper.justClickedLeft = false;
            }

            if (this.hb.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }

        }
    }

    private void openScreen() {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            if (Shadowverse.banCardScreen == null) {
                Shadowverse.banCardScreen = new ViewBanCardScreen();
            }

            ArrayList<AbstractCard> list = new ArrayList<>();
            Shadowverse.logger.info("start open");

            for (int i = 0; i < ((AbstractShadowversePlayer) AbstractDungeon.player).cardPool.getGroupCount(); ++i) {
                if (Shadowverse.groupActive[i]) {
                    list.add(((AbstractShadowversePlayer) AbstractDungeon.player).cardPool.copy(i));
                    Shadowverse.logger.info(((AbstractCard) list.get(list.size() - 1)).name);
                }
            }

            Shadowverse.banCardScreen.open(list);
        }
    }

    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            if (this.hb.hovered && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                TipHelper.renderGenericTip(1550.0F * Settings.scale, (float) Settings.HEIGHT - 120.0F * Settings.scale, TEXT[0], TEXT[1]);
            }

            sb.setColor(Color.WHITE);
            sb.draw(BAN_CARD_VIEW, (float) Settings.WIDTH - (64.0F * Settings.scale + 10.0F * Settings.scale) * 4.0F - 32.0F + 32.0F * Settings.scale, (float) Settings.HEIGHT - 64.0F * Settings.scale - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.angle, 0, 0, 64, 64, false, false);
            if (this.hb.hovered) {
                sb.setBlendFunction(770, 1);
                sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
                sb.draw(BAN_CARD_VIEW, (float) Settings.WIDTH - (64.0F * Settings.scale + 10.0F * Settings.scale) * 4.0F - 32.0F + 32.0F * Settings.scale, (float) Settings.HEIGHT - 64.0F * Settings.scale - 32.0F + 32.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, this.angle, 0, 0, 64, 64, false, false);
                sb.setBlendFunction(770, 771);
            }

            this.hb.render(sb);
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ViewBanCardOption");
        TEXT = uiStrings.TEXT;
        BAN_CARD_VIEW = ImageMaster.loadImage("img/ui/view_ban_card.png");
    }
}
