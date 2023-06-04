package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.SalvationLimonia;
import shadowverse.characters.AbstractShadowversePlayer;

public class LimoniaPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:LimoniaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:LimoniaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean upgraded;

    public LimoniaPower(AbstractCreature owner,boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/LimoniaPower.png");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&card.type== AbstractCard.CardType.ATTACK){
            flash();
            AbstractCard c = new SalvationLimonia();
            if (this.upgraded){
                c.upgrade();
            }
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
        }
    }

    public void updateDescription() {
        if (this.upgraded){
            this.description = DESCRIPTIONS[1];
        }else {
            this.description = DESCRIPTIONS[0];
        }
    }
}
