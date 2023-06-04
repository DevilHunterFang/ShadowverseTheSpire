package shadowverse.cards.Neutral.Temp;

import basemod.abstracts.CustomCard;
import shadowverseCharbosses.bosses.KMR.KMR;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class VictoryCard extends CustomCard {
    public static final String ID = "shadowverse:VictoryCard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VictoryCard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/VictoryCard.png";

    public VictoryCard() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
        this.isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn(){
        addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        boolean isKMR = false;
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m instanceof KMR){
                isKMR = true;
            }
        }
        if (isKMR&&(AbstractDungeon.getCurrRoom()).cannotLose){
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)mo, 99999));
                }
            }
        }else {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLDENROD, true)));
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect()));
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 0.9F));
            (AbstractDungeon.getCurrRoom()).cannotLose = false;
            CardCrawlGame.screenShake.rumble(4.0F);
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (m.isEscaping || m.isDead)
                    continue;
                m.useFastShakeAnimation(5.0F);
                AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
            }
        }
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
