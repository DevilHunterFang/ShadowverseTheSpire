package shadowverse.cards.Royal.Ambush;



import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.cards.Neutral.Status.EvolutionPoint;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;


public class ShadowedMemories extends CustomCard {
    public static final String ID = "shadowverse:ShadowedMemories";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ShadowedMemories.png";

    public ShadowedMemories() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new EvolutionPoint();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(1);
            upgradeMagicNumber(1);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean ambush = false;
        for (AbstractOrb o:abstractPlayer.orbs){
            if (o instanceof AmbushMinion && ((AmbushMinion) o).ambush){
                ambush = true;
                break;
            }
        }
        if (ambush){
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
        }
        addToBot((AbstractGameAction) new SFXAction("ShadowedMemories"));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer,this.block));
        addToBot((AbstractGameAction) new DrawCardAction(this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new ShadowedMemories();
    }
}

