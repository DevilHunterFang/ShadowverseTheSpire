package shadowverse.cards.Dragon.Armed;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Neutral.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Dragon;
import shadowverse.powers.ByronPower;


public class Byron extends CustomCard {
    public static final String ID = "shadowverse:Byron";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Byron");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Byron.png";

    public Byron() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Dragon.Enums.COLOR_BROWN, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = new ProductMachine();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("Byron"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new ByronPower(abstractPlayer,1)));
        if (upgraded){
            addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Byron();
    }
}

