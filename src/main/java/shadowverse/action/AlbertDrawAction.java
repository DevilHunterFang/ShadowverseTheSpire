package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Royal.Levin.Albert;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

public class AlbertDrawAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> nameTmp = new ArrayList<>();
    private ArrayList<AbstractCard> finalList = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public AlbertDrawAction(int amount){
        this.p = AbstractDungeon.player;
        setValues((AbstractCreature) this.p, (AbstractCreature) this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }


    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                addToBot(new AlbertDiscardAction(11 - p.hand.group.size()));
                this.isDone = true;
                return;
            }
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.drawPile.group) {
                if ((c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)&& c.cardID != Albert.ID && c.type == AbstractCard.CardType.ATTACK)) {
                    tmp.addToRandomSpot(c);
                }
            }
            if (tmp.size() == 0) {
                addToBot(new AlbertDiscardAction(11 - p.hand.group.size()));
                this.isDone = true;
                return;
            }
            for (int i = 0; i < this.amount; i++) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    AbstractCard card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        card.setCostForTurn(0);
                        card.applyPowers();
                        this.p.drawPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                    addToBot(new AlbertDiscardAction(11 - p.hand.group.size()));
                    this.isDone = true;
                }
                addToBot(new AlbertDiscardAction(11 - p.hand.group.size()));
                tickDuration();
            }
        }
    }
}

