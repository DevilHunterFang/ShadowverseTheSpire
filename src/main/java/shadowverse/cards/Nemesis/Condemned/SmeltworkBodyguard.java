package shadowverse.cards.Nemesis.Condemned;



import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractEnhanceCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;



public class SmeltworkBodyguard
        extends AbstractEnhanceCard {
    public static final String ID = "shadowverse:SmeltworkBodyguard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SmeltworkBodyguard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SmeltworkBodyguard.png";

    public SmeltworkBodyguard() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF, 2);
        this.baseBlock = 8;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }


    @Override
    public void exEnhanceUse(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void enhanceUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("SmeltworkBodyguard_EH"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new GainBlockAction(p,this.magicNumber));
        addToBot(new DrawCardAction(2));
    }

    @Override
    public void baseUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("SmeltworkBodyguard"));
        addToBot(new GainBlockAction(p,this.block));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SmeltworkBodyguard();
    }
}

