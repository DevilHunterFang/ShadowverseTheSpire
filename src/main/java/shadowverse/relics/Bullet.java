package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Neutral.Temp.AbsoluteOrder;
import shadowverse.cards.Neutral.Temp.VerdictWord;

public class Bullet
        extends CustomRelic {
    public static final String ID = "shadowverse:Bullet";
    public static final String IMG = "img/relics/Bullet.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Bullet_Outline.png";
    private static boolean triggered = false;
    private static AbstractCard verdict = new VerdictWord();
    private static AbstractCard order = new AbsoluteOrder();

    public Bullet() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        if (this.triggered)
            return this.DESCRIPTIONS[2];
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new Bullet();
    }

    public void changeDescription(){
        this.triggered = true;
        this.description = DESCRIPTIONS[2];
        this.flavorText = DESCRIPTIONS[1];
        this.pulse = true;
        this.update();
    }

    @Override
    public void atBattleStart() {
        if (triggered)
            this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (triggered){
            this.counter++;
            switch (this.counter){
                case 1:
                    addToBot(new SFXAction("VerdictWord"));
                    addToBot(new MakeTempCardInHandAction(verdict.makeStatEquivalentCopy()));
                    break;
                case 2:
                    addToBot(new SFXAction("AbsoluteOrder"));
                    addToBot(new MakeTempCardInHandAction(order.makeStatEquivalentCopy()));
                    break;
                case 3:
                    addToBot(new MakeTempCardInHandAction(verdict.makeStatEquivalentCopy()));
                    break;
                case 4:
                    addToBot(new MakeTempCardInHandAction(order.makeStatEquivalentCopy()));
                    break;
                case 5:
                default:
                    boolean hasVerdict = false;
                    boolean hasOrder = false;
                    for (AbstractCard card: AbstractDungeon.player.hand.group){
                        if (card instanceof VerdictWord){
                            hasVerdict = true;
                        }
                        if (card instanceof AbsoluteOrder){
                            hasOrder = true;
                        }
                    }
                    if (!hasVerdict){
                        addToBot(new MakeTempCardInHandAction(verdict.makeStatEquivalentCopy()));
                    }
                    if (!hasOrder){
                        addToBot(new MakeTempCardInHandAction(order.makeStatEquivalentCopy()));
                    }
                    break;
            }
        }
    }


    @Override
    public void onVictory() {
        this.counter = -1;
    }
}
