package shadowverse.cards.Necromancer.Ghosts;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.NecromanceAction;
import shadowverse.cards.Neutral.Status.Ghost;
import shadowverse.characters.Necromancer;


public class TrickDullahan
        extends CustomCard {
    public static final String ID = "shadowverse:TrickDullahan";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TrickDullahan");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TrickDullahan.png";


    public TrickDullahan() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 9;
        this.cardsToPreview = new Ghost();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("TrickDullahan"));
        addToBot(new GainBlockAction(abstractPlayer,this.baseBlock));
        addToBot(new NecromanceAction(1,null, new DrawCardAction(1), new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy())));
        if (this.upgraded){
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TrickDullahan();
    }
}


