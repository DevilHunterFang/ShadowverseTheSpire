package shadowverse.cards.Vampire.Evolve;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;


public class Gadel extends CustomCard {
    public static final String ID = "shadowverse:Gadel";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Gadel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Gadel.png";

    public Gadel() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new EvolutionPoint();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Gadel"));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        addToBot(new DestroyAction(1,new DrawCardAction(this.magicNumber)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Gadel();
    }
}

