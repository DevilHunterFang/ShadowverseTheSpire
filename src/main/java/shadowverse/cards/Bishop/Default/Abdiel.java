package shadowverse.cards.Bishop.Default;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Bishop;
import shadowverse.powers.AbdielPower;

public class Abdiel
        extends CustomCard {
    public static final String ID = "shadowverse:Abdiel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Abdiel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Abdiel.png";

    public Abdiel() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        AbstractCard ep = new EvolutionPoint();
        ep.upgrade();
        this.cardsToPreview = ep;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Abdiel"));
        addToBot(new VFXAction(new RainbowCardEffect()));
        addToBot(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        addToBot( new ApplyPowerAction(p, p, new AbdielPower(p)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Abdiel();
    }
}


