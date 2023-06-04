package shadowverse.cards.Nemesis.Mech;

import basemod.abstracts.CustomCard;
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
import shadowverse.characters.Nemesis;

public class RoboticsReporter
        extends CustomCard {
    public static final String ID = "shadowverse:RoboticsReporter";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RoboticsReporter");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RoboticsReporter.png";

    public RoboticsReporter() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.cardsToPreview = new ProductMachine();
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.tags.add(AbstractShadowversePlayer.Enums.ACADEMIC);
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("RoboticsReporter"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),3));
    }


    public AbstractCard makeCopy() {
        return  new RoboticsReporter();
    }
}


