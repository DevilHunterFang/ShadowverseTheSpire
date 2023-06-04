package shadowverse.cards.Bishop.Amulet1;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.Neutral.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;


public class MajorPrayers
        extends CustomCard {
    public static final String ID = "shadowverse:MajorPrayers";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MajorPrayers");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MajorPrayers.png";

    public MajorPrayers() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard)new Munyaru();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int amt = 0;
        for (AbstractOrb o:abstractPlayer.orbs){
            if (o instanceof AmuletOrb && ((AmuletOrb) o).amulet.type!=CardType.CURSE && !((AmuletOrb) o).amulet.hasTag(AbstractShadowversePlayer.Enums.MINION)){
                amt++;
            }
        }
        if (amt>=2){
            addToBot((AbstractGameAction)new SFXAction("MajorPrayers"));
            addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }else{
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            AbstractCard m = new MunyaruRaid();
            AbstractCard e = new ErisPrayer();
            if (this.upgraded){
                m.upgrade();
                e.upgrade();
            }
            stanceChoices.add(m);
            stanceChoices.add(e);
            addToBot((AbstractGameAction) new ChooseOneAction(stanceChoices));
        }

    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MajorPrayers();
    }
}

