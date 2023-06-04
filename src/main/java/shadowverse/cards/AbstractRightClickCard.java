package shadowverse.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import shadowverse.characters.AbstractShadowversePlayer;

public abstract class AbstractRightClickCard extends AbstractNeutralCard {
    private boolean RclickStart;
    private boolean Rclick;
    private boolean dCheck;

    private long lastClick;
    private static final int DURATION = 300;

    public AbstractRightClickCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.Rclick = false;
        this.RclickStart = false;
        this.dCheck = false;
        this.tags.add(AbstractShadowversePlayer.Enums.FUSION);
    }

    protected abstract void onRightClick();

    protected void onDoubleRightClick() {
    }

    private long deltaTime() {
        return System.currentTimeMillis() - this.lastClick;
    }

    private boolean doubleClick() {
        boolean b = this.deltaTime() < DURATION;
        this.lastClick = System.currentTimeMillis();
        return b;
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player == null){
            return;
        }else {
            if (this.RclickStart && InputHelper.justReleasedClickRight) {
                if (this.hb.hovered) {
                    this.Rclick = true;
                }
                this.RclickStart = false;
            }
            if ((AbstractDungeon.player.hand.group.contains(this)) && (this.hb != null) && ((this.hb.hovered) && (InputHelper.justClickedRight))) {
                this.RclickStart = true;
            }
            if (this.deltaTime() >= DURATION && this.dCheck) {
                this.dCheck = false;
                this.onRightClick();
            }
            if ((this.Rclick)) {
                this.Rclick = false;
                this.dCheck = true;
                if (this.doubleClick()) {
                    this.onDoubleRightClick();
                }
            }
        }
    }
}
