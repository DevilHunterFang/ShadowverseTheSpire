package shadowverse.cards.Curse;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.orbs.AmuletOrb;

public class Indulgence extends CustomCard {
    public static final String ID = "shadowverse:Indulgence";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Indulgence");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Indulgence.png";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public Indulgence() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) return false;
        if (p.gold<12) {
            canUse = false;
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        }
        return canUse;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],true,true,card -> {
            return true;
        },abstractCards -> {
            for (AbstractCard c:abstractCards){
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.hand));
                CardCrawlGame.sound.play("GOLD_JINGLE");
                abstractPlayer.gold -= 12;
            }
        }));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Indulgence();
    }
}
