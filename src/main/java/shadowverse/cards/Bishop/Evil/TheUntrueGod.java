package shadowverse.cards.Bishop.Evil;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Neutral.Curse.EvilWorship;
import shadowverse.characters.Bishop;

public class TheUntrueGod
        extends CustomCard {
    public static final String ID = "shadowverse:TheUntrueGod";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheUntrueGod");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheUntrueGod.png";

    public TheUntrueGod() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.cardsToPreview = new EvilWorship();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("TheUntrueGod"));
        addToBot((AbstractGameAction)new GainEnergyAction(this.magicNumber));
        for (int i=0;i<p.maxOrbs;i++){
            addToBot((AbstractGameAction)new PlaceAmulet(this.cardsToPreview.makeStatEquivalentCopy(),null));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheUntrueGod();
    }
}


