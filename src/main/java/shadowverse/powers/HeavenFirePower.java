package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.Neutral.Temp.HeavenFire;

public class HeavenFirePower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:HeavenFirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:HeavenFirePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean hasTriggered = true;
    private boolean upgraded;
    public HeavenFirePower(AbstractCreature owner,boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/HeavenFirePower.png");
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!hasTriggered){
            if (card instanceof AbstractAmuletCard || card instanceof AbstractNoCountDownAmulet || (card instanceof AbstractCrystalizeCard && card.type== AbstractCard.CardType.POWER)){
                hasTriggered = true;
                AbstractCard h = new HeavenFire();
                if (this.upgraded){
                    h.upgrade();
                }
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(h));
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        this.hasTriggered = false;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+(this.upgraded?HeavenFire.NAME+"+":HeavenFire.NAME)+DESCRIPTIONS[1];
    }
}
