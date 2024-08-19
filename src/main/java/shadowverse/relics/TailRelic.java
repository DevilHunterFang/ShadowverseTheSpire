package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import shadowverse.cards.Neutral.Temp.Ginsetsu;
import shadowverse.cards.Neutral.Temp.OneTailFox;


public class TailRelic
        extends CustomRelic {
    public static final String ID = "shadowverse:TailRelic";
    public static final String IMG = "img/relics/TailRelic.png";
    public static final String OUTLINE_IMG = "img/relics/outline/TailRelic_Outline.png";
    public static AbstractCard c = new OneTailFox();

    public TailRelic() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.SHOP, LandingSound.MAGICAL);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atBattleStart() {
        if (!this.grayscale) {
            if (this.counter < 9) {
                flash();
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, (AbstractRelic) this));
                addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true));
            } else {
                flash();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Ginsetsu(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                this.grayscale = true;
            }
        }
    }


    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.cardID.equals(TailRelic.c.cardID) && !this.grayscale) {
            this.counter += 1;
        }
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new TailRelic();
    }
}


