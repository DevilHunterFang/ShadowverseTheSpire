package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Neutral.Temp.ShadowCommander;
import shadowverse.cards.Neutral.Temp.ShadowSoldier;

public class BrandOfMorningStarPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:BrandOfMorningStarPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:BrandOfMorningStarPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean check = true;

    public BrandOfMorningStarPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/BrandOfMorningStarPower.png");
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (check&&card.type== AbstractCard.CardType.ATTACK){
            int amt=0;
            for (AbstractCard c: AbstractDungeon.player.exhaustPile.group){
                if (c.type== AbstractCard.CardType.ATTACK)
                    amt++;
            }
            if (amt>=15)
                addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new ShadowCommander()));
            else
                addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new ShadowSoldier()));
            check=false;
        }
    }

    @Override

    public void atStartOfTurnPostDraw() {
        check=true;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
